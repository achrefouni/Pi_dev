/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package github;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    
      public void insert(String nom,String prenom,String quest,String mail,String mdp,int tel,int cin,int age){

        String requete = "insert into citoyens values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, quest);
            ps.setString(2, mdp);
            ps.setInt(3,cin );
            ps.setString(4, nom);
            ps.setString(5, prenom);
            ps.setInt(6, age);
            ps.setString(7, mail);
            ps.setInt(8, tel);
            
            ps.executeUpdate();
              JOptionPane.showMessageDialog(null, "Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
              JOptionPane.showMessageDialog(null, "erreur lors de l'insertion "+ex.getMessage());
        }
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
    }

    
}
