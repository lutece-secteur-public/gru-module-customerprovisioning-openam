package fr.paris.lutece.plugins.gru.modules.openam.services;
import fr.paris.lutece.plugins.gru.modules.supply.business.IUserInfoProvider;
import fr.paris.lutece.plugins.openamidentityclient.business.Identity;
import fr.paris.lutece.plugins.openamidentityclient.service.IOpenamIdentityService;
import fr.paris.lutece.plugins.openamidentityclient.service.OpenamIdentityException;
import fr.paris.lutece.plugins.openamidentityclient.service.OpenamIdentityService;
import fr.paris.lutece.portal.service.util.AppLogService;


/**
 *
 * @author root
 */
public class OpenAmService implements IUserInfoProvider{    

        private final IOpenamIdentityService _OpenAmIdentityService= OpenamIdentityService.getService();
      
    @Override
    public Object getUserInfo(String guid) {   
       Identity oIdentity = null;
            try {
                oIdentity = _OpenAmIdentityService.getIdentity(guid);
            } catch (OpenamIdentityException ex) {            
                
                 AppLogService.info(ex.getStackTrace());
            }      
     
     return oIdentity;
    }
    

 
}
