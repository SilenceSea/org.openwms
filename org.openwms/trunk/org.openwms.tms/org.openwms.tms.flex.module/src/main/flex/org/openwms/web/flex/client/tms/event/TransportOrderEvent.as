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
package org.openwms.web.flex.client.tms.event {
	
    import flash.events.Event;

    /**
     * A TransportOrderEvent.
     *
     * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
     * @version $Revision$
     * @since 0.1
     */
    public class TransportOrderEvent extends Event {
    	
        public static const LOAD_TRANSPORT_ORDERS:String = "LOAD_TRANSPORT_ORDERS";
        public static const CREATE_TRANSPORT_ORDER:String = "CREATE_TRANSPORT_ORDER";
        public static const DELETE_TRANSPORT_ORDER:String = "DELETE_TRANSPORT_ORDER";
        public static const CANCEL_TRANSPORT_ORDER:String = "CANCEL_TRANSPORT_ORDER";
        public static const REDIRECT_TRANSPORT_ORDER:String = "REDIRECT_TRANSPORT_ORDER";
        
        public var data:*;

        public function TransportOrderEvent(type:String, bubbles:Boolean = true, cancelable:Boolean = false) {
            super(type, bubbles, cancelable);
        }
    }
}