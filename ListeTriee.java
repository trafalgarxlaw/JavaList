
/**
 * HASY04089702
 * YASSINE HASNAOUI

 */
import java.util.ArrayList;
import java.util.NoSuchElementException;


// implementation de l'interface
public class ListeTriee <T extends Comparable> implements IListeTriee <T> {
    ArrayList<T> elements = new ArrayList<>();
    int position=-1;
    @Override
    public int ajouter(IListeTriee<T> autreListe) {
        int nbrAjoute=0;
        int posElem;
        int posautreliste;
        if(autreListe==null){
            throw new NullPointerException();
        }
        autreListe.debut();//on place la position courante de autre liste au debut
        do {//tant quon peut deplacer la position courante
           if (elements.contains(autreListe.elementCourant())){
                //si notre liste contient deja lelement a la position courante de autre liste on ajoute pas
           }else{//sinon on ajoute selon lordre de notre liste lelement a la position courante de autre liste
             posElem=0;
            while (posElem < elements.size() && (elements.get ( posElem )).compareTo(autreListe.elementCourant())< 0 ){
                posElem++;//on avance la position tsnt que l'element de lautre liste ne trouve un autre element plus grand que lui meme
            }
            elements.add(posElem,autreListe.elementCourant());//on ajoute
            nbrAjoute++;// cest le nbr delement ajoute
        }
          }while(autreListe.suivant());
              position=0;
             return nbrAjoute;
    }

    @Override
    public boolean ajouter(T element) {
        int pos;
        if(element==null){// si lelement a ajouter est null,lance une exeption
            throw new NullPointerException();
        }else if (elements.contains(element)){
            return false;
        }else {
            pos=0;
            while(pos < elements.size() && (elements.get ( pos )).compareTo ( element ) < 0 ){// redefinition de compare to deja faite.
                pos++;
            }
            //lelement a inserer est plus petit que celui a la position
            elements.add ( pos, element );
            position=pos;// la position courante devient celle ou on a ajouter lelement
            return true;
        }

    }

    @Override
    public void debut() {
        if(position==-1){
            throw new ListeVideException()  ;
        }else{
            position=0;
        }
    }


    @Override
    public T elementCourant() {
        T elementC;
        if(position==-1){
            throw new ListeVideException()  ;
        }else{

            elementC=elements.get(position);

        }

        return elementC;

    }

    @Override
    public boolean elementExiste(T element) {
        return elements.contains(element);
    }

    @Override
    public IListeTriee<T> elementsCommuns(IListeTriee<T> autreListe) {
        IListeTriee<T> listecomm = new ListeTriee<T>();
        if(autreListe==null){
            throw new NullPointerException();
        }else {
            autreListe.debut();
            do {
                if(elements.contains(autreListe.elementCourant())){
                    if(listecomm.elementExiste(autreListe.elementCourant())){

                    }else{
                        listecomm.ajouter(autreListe.elementCourant());
                    }
                }

            }while(autreListe.suivant());


        }
        if(!(listecomm.estVide())){
            listecomm.debut();
        }
        return listecomm;
    }

    @Override
    public boolean estVide() {
        boolean vide=false;
        if(position==-1){
            vide=true;
        }
        return vide;
    }

    @Override
    public void fin() {
        if(position==-1){
            throw new ListeVideException()  ;
        }else{
            position=elements.size()-1;//-1 ??
        }

    }

    @Override
    public int nbrElements() {
        return elements.size();
    }

    @Override
    public void positionner(T element) {
        if(position==-1){
            throw new ListeVideException()  ;
        }else if(element==null){
            throw new NullPointerException();
        }else if (!(elements.contains(element))){
            throw new NoSuchElementException();
        }else{

            position=elements.lastIndexOf(element);
        }

    }

    @Override
    public boolean precedent() {
        if(position==-1){
            throw new ListeVideException()  ;
        }else if(position==0){
            return false;

        }else{
            position=position-1;
            return true;
        }

    }

    @Override
    public IListeTriee<T> sousListe(T elementDebut, T elementFin) {
        int posdeb;
        int posfin;
        int pos;
        int i;
        IListeTriee<T> sousliste = new ListeTriee<T>();//on cree une sous liste de type listetriee
        if(position==-1){
            throw new ListeVideException()  ;
        }else if(elementDebut==null || elementFin==null){
            throw new NullPointerException();
        }else if (!(elementDebut.compareTo(elementFin)<0)){
            throw new NoSuchElementException();
        }else{
            pos=0;
            while(pos<elements.size()){
                if(elements.get(pos).compareTo(elementDebut)>=0&&elements.get(pos).compareTo(elementFin)<=0){
                    sousliste.ajouter(elements.get(pos));
                }
            pos++;
            }


        }
        if (!(sousliste.estVide())){
            sousliste.debut();
        }
        return sousliste;
    }

    @Override
    public boolean suivant() {
        if(position==-1){
            throw new ListeVideException()  ;
        }else if(position==elements.size()-1){
            return false;

        }else{
            position=position+1;
            return true;
        }
    }

    @Override
    public T supprimer() {
        T elemsupp;
        if(position==-1){
            throw new ListeVideException()  ;
        }else {
            elemsupp=elements.get(position);
            elements.remove(position);
            if(position==0){
                if(elements.isEmpty())
                    position=-1;
            }else{
                position--;
                if(elements.isEmpty())
                    position=-1;
            }

        }
        return elemsupp;
    }

    @Override
    public IListeTriee<T> supprimer(IListeTriee<T> autreListe) {
        int posAsup;
        if(autreListe==null){
            throw new NullPointerException();
        }
        IListeTriee<T> listesup = new ListeTriee<T>();
        autreListe.debut();
        while(autreListe.suivant()){
            if(elements.contains(autreListe.elementCourant())){
                elements.remove(elements.lastIndexOf(autreListe.elementCourant()));
                listesup.ajouter(autreListe.elementCourant());

            }
        }
        if (!(listesup.estVide())){
            listesup.debut();
        }
        if(elements.isEmpty()){
            position=-1;
        }  else{
            position=0;
        }
        return listesup;
    }

    @Override
    public boolean supprimer(T element) {
        int PosAsup;
        if(position==-1){
            throw new ListeVideException()  ;
        }else if(element==null){
            throw new NullPointerException();
        }else if (!(elements.contains(element))){
            return false;
        }else{
            PosAsup=elements.indexOf(element);
            elements.remove(PosAsup);
            position=PosAsup;
            if(position==0){
                if(elements.isEmpty())
                    position=-1;
            }else{
                position--;
                if(elements.isEmpty())
                    position=-1;
            }
            return true;

        }

    }

    @Override
    public void vider() {
        int i=0;
        do{
            elements.remove(i);

        }while(!(elements.isEmpty()));
        position=-1;
    }
    /**
     * <p>Retourne une representation de cette liste sous forme d'une chaine de
     * caracteres, selon le format montre ci-dessous. </p>
     *
     * <pre>
     * Format de la chaine retournee :
     *
     *    "[]"                                   (si cette liste est vide)
     *    "[E1, E2, ..., En] (element courant)"  (si cette liste n'est pas vide)
     *
     * Exemple : Soit cette liste = [2, 3, 7, 9, 12, 25, 36, 42] dont l'element
     *           courant est 9. L'appel de toString sur cette liste retournera
     *           la chaine "[2, 3, 7, 9, 12, 25, 36, 42] (9)"
     * </pre>
     * @return une representation de cette liste sous forme d'une chaine de
     *         caracteres.
     */

    @Override
    public String toString () {
        String s;
        if (elements.isEmpty()) {
            s = "[]";
        } else {
            s = elements.toString() + " (" + elements.get(position) + ")";
        }
        return s;
    }




}

