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
    public String FindID(String id){
        Administrator p=em.find(Administrator.class, id);
        String ID=p.getId();
        if (ID != null) {
            return ID;
        } else {
            return null;
        }
    }
    
    public String FindPassword(String ID,String password){
        Administrator p=em.find(Administrator.class, ID);
        String Password=p.getPassword();
        if(Password.equals(password)){
            return Password;
        }else{
            return null;
        }
    }
    
    public Administrator findAdminiById(String administratorid){
        Administrator admini=new Administrator();
        try{
            admini=(Administrator) em.createNamedQuery("Administrator.findById").setParameter("id", administratorid).getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return admini;
    }

//    public List<String> findExistedAdminis() {
//        List<String> adminis = new ArrayList<>();
//        try {
//            List<Administrator> a=em.createNamedQuery("Administrator.findAll").getResultList();
//            a.forEach((admini)->{
//                adminis.add(admini.getId());
//            });
//        } catch (Exception e) {
//            throw new EJBException(e.getMessage());
//        }
//        return adminis;
//    }
}
