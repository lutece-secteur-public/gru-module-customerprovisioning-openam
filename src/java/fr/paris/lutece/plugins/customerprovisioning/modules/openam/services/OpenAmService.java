/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.customerprovisioning.modules.openam.services;


import fr.paris.lutece.plugins.customerprovisionning.business.UserDTO;
import fr.paris.lutece.plugins.customerprovisionning.services.IUserInfoProvider;
import fr.paris.lutece.plugins.openamidentityclient.business.Account;
import fr.paris.lutece.plugins.openamidentityclient.business.Identity;
import fr.paris.lutece.plugins.openamidentityclient.service.OpenamIdentityException;
import fr.paris.lutece.plugins.openamidentityclient.service.OpenamIdentityService;
import fr.paris.lutece.portal.service.util.AppLogService;/**
 *
 * OpenAmService service link GRU SUpply and open AM
 */
public class OpenAmService implements IUserInfoProvider
{  
  
     /** 
     * @param strguid to make request
     * @return UserDTO else null
     */
    @Override
    public UserDTO getUserInfo( String strguid )
    {
        Identity oIdentity;
        Account oAccount;
        UserDTO oIUserDTO = null;

        try
        {
            oIdentity = OpenamIdentityService.getService(  ).getIdentity( strguid );
            oAccount = OpenamIdentityService.getService(  ).getAccount( strguid );

            if ( ( oIdentity != null ) && ( oAccount != null ) )
            {
                oIUserDTO = populateIdentityAndUserDTO( oIdentity, oAccount );
            }
        }
        catch ( OpenamIdentityException ex )
        {
            AppLogService.info( ex.getStackTrace(  ) );
        }

        return oIUserDTO;
    }

    /**
     * @param oIdentity identity of user
     * @param oAccount Account of user
     * @return oIUserDTO populate of user
     */
    private UserDTO populateIdentityAndUserDTO( Identity oIdentity, Account oAccount )
    {
        UserDTO oIUserDTO = new UserDTO(  );
        oIUserDTO.setBirthday( oIdentity.getBirthday(  ) );
        oIUserDTO.setCity( oIdentity.getCity(  ) );
        oIUserDTO.setCityOfBirth( oIdentity.getCityOfBirth(  ) );
        oIUserDTO.setCivility( oIdentity.getCivility(  ) );
        oIUserDTO.setEmail( oAccount.getLogin(  ) );
        oIUserDTO.setFirstname( oIdentity.getFirstname(  ) );
        oIUserDTO.setLastname( oIdentity.getLastname(  ) );
        oIUserDTO.setPostalCode( oIdentity.getPostalCode(  ) );
        oIUserDTO.setStayConnected( oIdentity.getStayConnected(  ) );
        oIUserDTO.setStreet( oIdentity.getStreet(  ) );
        oIUserDTO.setTelephoneNumber( oIdentity.getTelephoneNumber(  ) );
        oIUserDTO.setUid( oIdentity.getUid(  ) );

        return oIUserDTO;
    }
}
