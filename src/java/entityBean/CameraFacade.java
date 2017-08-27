/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author QIULI
 */
@Stateless
public class CameraFacade extends AbstractFacade<Camera> {

    @PersistenceContext(unitName = "crossguruPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CameraFacade() {
        super(Camera.class);
    }
    
}
