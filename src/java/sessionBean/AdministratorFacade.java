/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Administrator;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nicole Yang
 */
@Stateless
public class AdministratorFacade extends AbstractFacade<Administrator> {

    @PersistenceContext(unitName = "crossguruPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorFacade() {
        super(Administrator.class);
    }

    public List<String> findExistedAdminis() {
        List<String> adminis = new ArrayList<>();
        try {
            List<Administrator> a=em.createNamedQuery("Administrator.findAll").getResultList();
            a.forEach((admini)->{
                adminis.add(admini.getId());
            });
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return adminis;
    }
}
