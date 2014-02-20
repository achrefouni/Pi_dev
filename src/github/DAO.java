/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package github;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Doct
 */
public class DAO {
    boolean test(String a,String b,int c)
        {    
        Personne ok;
        ok= findByData(a,b,c);
        return a.equals(ok.getLogin())&& b.equals(ok.getPasse());       
        }
     public Personne findByData(String login,String pwd,int a){
     Personne p = new Personne();
     PreparedStatement ps;
     String requete="";
     if(a==1){ requete = "select login,pwd from citoyens where login=?  and pwd=? ";}
     if(a==2){ requete = "select login,pwd from responsable_etablisement where login=?  and pwd=? ";}
     if(a==3){ requete = "select login,pwd from admin where login=?  and pwd=? ";}
     
        try {
            try {
              ps = MyConnection.getInstance().prepareStatement(requete);
            } catch (Exception ex) {
            System.out.println("erreur lors ");
            return null;
        }
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                p.setLogin(resultat.getString(1));
                p.setPasse(resultat.getString(2));
            }
            return p;

        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }}
