/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Police;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nicole Yang
 */
@Stateless
public class PoliceFacade extends AbstractFacade<Police> {

    @PersistenceContext(unitName = "crossguruPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoliceFacade() {
        super(Police.class);
    }
    
    public String FindID(String id){
        Police p=em.find(Police.class, id);
        String ID=p.getId();
        if (ID != null) {
            return ID;
        } else {
            return null;
        }
    }
    
    public String FindPassword(String ID,String password){
        Police p=em.find(Police.class, ID);
        String Password=p.getPassword();
        if(Password.equals(password)){
            return Password;
        }else{
            return null;
        }
    }
    
    public Police findPoliceById(String policeid){
        Police police=new Police();
        try{
            police=(Police) em.createNamedQuery("Police.findById").setParameter("id", policeid).getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return police;
    }
    
}
