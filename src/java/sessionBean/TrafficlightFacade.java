/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

<<<<<<< HEAD
import entity.Trafficlight;
=======
import entityBean.Trafficlight;
>>>>>>> origin/qiuli
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
<<<<<<< HEAD
 * @author Nicole Yang
=======
 * @author QIULI
>>>>>>> origin/qiuli
 */
@Stateless
public class TrafficlightFacade extends AbstractFacade<Trafficlight> {

    @PersistenceContext(unitName = "crossguruPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrafficlightFacade() {
        super(Trafficlight.class);
    }
    
}
