import java.util.NoSuchElementException;
public class ListeTrieeChainee <T extends Comparable> implements IListeTriee <T> {



    //initialisation lorsque la listeest vide

    Maillon elements=null; // represente le premier maillon de la listeTriee
    int  nbElements=0;
    Maillon position=null;//represente le maillon courant de la liste,initialise a null







    @Override
    public boolean ajouter(T element) {
        boolean bool=true;
        if(element==null){// si lelement a ajouter est null,lance une exeption
            throw new NullPointerException();
        }else{
            position=elements;// on place la pos courante au prem elem
            while (position.suivant()!=null){// tant que lelem suiv de la pos cour nest pas null

                if(position.info().equals(element)){ // si lelem a la pos cour est egale a lelem quon veut ajouter
                    // dans ce cas on ne doit pas avoir de doublons donc on ajoute pas
                    bool=false;
                }

                    position=position.suivant();  // on modifie la pos courante a lelem suiv

            }


        }

        if (bool){  // on fait lajout
            int pos=0;
            int i=0;
            Maillon newmaillon;
            position=elements;// on place la pos courante au prem elem pour parcourir la chaine
            while ((position.suivant()!=null) && element.compareTo(position.info())> 0 ){



                position=position.suivant();// la pos courante pointe vers la chaine suivante
                pos++;


                 }
            // lelement est plus petit que lelement a position
            //cas 1
                 if (position!=elements&&position.suivant()!=null){// si la position a inserer nest pas au extremitee
            // donc on doit ajouter a lelement la position pos-1.
            // cas ou lelem a inserer ne se trouve pas au extremiter de la liste chainee
            // (inserstion milieu)
            pos=pos-1;
            position=elements;// on reajuste la position courante a lelem qui viens juste avant ou on doit faire linsersion
            while(i <pos){
                position=position.suivant();
                i++;
            }

           newmaillon= new Maillon(element,position.suivant()); //on cree notre nouv maillon qui pointe vers le maillon suivant la pos cour
             position.modifierSuivant(newmaillon);// on modifie le suivant du maillon pour quil pointe vers le nouveau qui a ete inserer
                 }
            // cas 2
            // insersion au debut
            if (position==elements){
                newmaillon= new Maillon(element,elements);
                elements.modifierSuivant(newmaillon);


            }
            // cas 3
            // insesion a la fin
            if(position.suivant()==null){
                newmaillon= new Maillon(element,null);
                position.modifierSuivant(newmaillon);




            }



            }






        return bool;
    }

    @Override
    public int ajouter(IListeTriee<T> autreListe) {
        return 0;
    }

    @Override
    public T elementCourant() {
        T elemcourant;
        if (elements==null){
            throw new ListeVideException()  ;
        }
        elemcourant=position.info();
        return position.info();
        }

    @Override
    public void positionner(T element) {

    }

    @Override
    public void debut() {

    }

    @Override
    public void fin() {

    }

    @Override
    public boolean precedent() {
        return false;
    }

    @Override
    public boolean suivant() {
        return false;
    }

    @Override
    public T supprimer() {
        return null;
    }

    @Override
    public boolean supprimer(T element) {
        return false;
    }

    @Override
    public IListeTriee<T> supprimer(IListeTriee<T> autreListe) {
        return null;
    }

    @Override
    public boolean elementExiste(T element) {
        return false;
    }

    @Override
    public int nbrElements() {
        return 0;
    }

    @Override
    public boolean estVide() {
        return false;
    }

    @Override
    public IListeTriee<T> sousListe(T elementDebut, T elementFin) {
        return null;
    }

    @Override
    public IListeTriee<T> elementsCommuns(IListeTriee<T> autreListe) {
        return null;
    }

    @Override
    public void vider() {

    }
}
