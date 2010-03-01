/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.service.spring;

import java.util.List;

import org.openwms.common.domain.Location;
import org.openwms.common.domain.LocationPK;
import org.openwms.common.domain.TransportUnit;
import org.openwms.common.domain.TransportUnitType;
import org.openwms.common.domain.values.Barcode;
import org.openwms.common.integration.GenericDao;
import org.openwms.common.service.TransportUnitService;
import org.openwms.common.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A TransportUnitServiceImpl.
 * 
 * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
 * @version $Revision$
 * 
 */
@Service
@Transactional
public class TransportUnitServiceImpl extends EntityServiceImpl<TransportUnit, Long> implements TransportUnitService {

    @Autowired
    @Qualifier("locationDao")
    private GenericDao<Location, Long> locationDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public TransportUnit createTransportUnit(Barcode barcode, TransportUnitType transportUnitType,
            LocationPK actualLocation) {
        TransportUnit transportUnit = dao.findByUniqueId(barcode);
        Location location = locationDao.findByUniqueId(actualLocation);
        if (transportUnit != null) {
            throw new ServiceException("TransportUnit with id " + barcode + " still exists");
        }
        if (location == null) {
            throw new ServiceException("Location " + actualLocation + " doesn't exists");
        }
        transportUnit = new TransportUnit(barcode);
        transportUnit.setTransportUnitType(transportUnitType);
        transportUnit.setActualLocation(location);
        dao.persist(transportUnit);
        return transportUnit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportUnit> getAllTransportUnits() {
        logger.debug("GetAllTransportUnits on service called");
        return dao.findAll();
    }

    /**
     * @see org.openwms.common.service.TransportUnitService#moveTransportUnit(org.openwms.common.domain.values.Barcode,
     *      org.openwms.common.domain.LocationPK)
     */
    @Override
    public void moveTransportUnit(Barcode barcode, LocationPK targetLocationPK) {
        TransportUnit transportUnit = dao.findByUniqueId(barcode);
        if (transportUnit == null) {
            throw new ServiceException("TransportUnit with id " + barcode + " not found");
        }
        Location actualLocation = locationDao.findByUniqueId(targetLocationPK);
        // if (actualLocation == null) {
        // throw new ServiceException("Location with id " + newLocationPk +
        // " not found");
        // }
        transportUnit.setActualLocation(actualLocation);
        // try {
        dao.save(transportUnit);
        // }
        // catch (RuntimeException e) {
        // throw new ServiceException("Cannot move TransportUnit with barcode "
        // + barcode + " to location "
        // + newLocationPk, e);
        // }
    }
}
