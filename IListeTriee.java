
import java.util.NoSuchElementException;

/**
 * <p>Interface decrivant les services d'une liste non indicee, sans doublons,
 * sans elements null, et dont les elements sont tries en ordre croissant. </p>
 *
 * <p><b>Notez</b> que lorsqu'un des services doit rechercher un element dans cette liste,
 * il est possible que ce soit la methode <i>public boolean equals (Object o)</i>
 * qui soit utilisee pour effectuer les tests d'egalite.</p>
 *
 * <p>Cette liste possede les caracteristiques suivantes : </p>
 * <ul>
 * <li>Elle n'accepte aucun doublons.</li>
 *
 * <li>Elle n'accepte pas d'elements null.</li>
 *
 * <li>Une liste ne contenant aucun element est dite vide.</li>
 *
 * <li>Les elements dans cette liste sont tries en ordre croissant : le premier
 * element (ou l'element en debut de liste) correspond a l'element le plus petit
 * dans cette liste, et le dernier element (ou l'element en fin de liste)
 * correspond a l'element le plus grand dans cette liste.</li>
 *
 * <li>Puisque cette liste est triee, les elements (de type T) dans cette liste doivent
 * implementer l'interface Comparable pour assurer la redefinition de la
 * methode <i>compareTo</i> : &lt;T extends Comparable&gt;.</li>
 *
 * <li>Lorsqu'il n'y a qu'un seul element dans cette liste, cet element est
 * a la fois le premier et le dernier element de cette liste.</li>
 *
 * <li>Les elements de cette liste ne sont pas tous associes a une position
 * (un indice) comme dans un tableau, par exemple. Plutot, cette liste maintient
 * une seule position, qu'on appelle <i>position courante</i>, qui marque toujours
 * l'emplacement d'un des elements de la liste (lorsque celle-ci n'est pas vide).</li>
 *
 * <li>On appelle <i>element courant</i> l'element qui se trouve a la position
 * courante.</li>
 *
 * <li>Une liste vide n'a pas de position courante (ou d'element courant).</li>
 * </ul>
 *
 * <p><b>NOTES SUR LES SERVICES DE POSITIONNEMENT</b><br>
 * (pour iterer sur les elements de la liste)</p>
 *
 * <p>Des services de positionnement sont offerts afin de pouvoir se deplacer dans
 * ce type de liste. Par exemple, la methode <i>debut</i> permet de placer la
 * position courante sur le premier element de la liste. De la meme maniere, la
 * methode <i>fin</i> permet de placer la position courante sur le dernier element
 * de la liste. La methode <i>positionner(T elem)</i> permet de placer la position courante
 * sur l'element donne, s'il existe dans la liste. Ensuite, des services comme
 * <i>precedent</i> et <i>suivant</i> permettent de deplacer la position courante
 * vers le debut, ou vers la fin de la liste. En utilisant la methode
 * <i>elementCourant</i> on peut alors obtenir l'element se trouvant a la position
 * courante.</p>
 *
 * <u>Exemple</u> :
 * Supposon une liste d'entiers non vide, disons liste, contenant les elements
 * suivants : [3, 8, 9, 11, 17, 23], et dont l'element courant est 11. Les instructions
 * suivantes permettent de parcourir les elements de cette liste pour les afficher.
 *
 * <pre>
 * //placer la position courante sur le premier element de la liste
 * liste.debut();
 *
 * //Afficher tous les elements de liste, dans l'ordre (3 8 9 11 17 23)
 * do {
 *    System.out.print(liste.elementCourant() + " ");
 * } while (liste.suivant());
 * </pre>
 *
 * <p><b>NOTES SUR LES SERVICES D'AJOUT ET DE SUPPRESSION</b></p>
 * <p>Les services d'ajout offerts maintiennent l'ordonnancement des elements
 * en ordre croissant ainsi que les caracteristiques decrites ci-haut : doublons
 * interdits, et elements null interdits. Les services d'ajout ou de suppression
 * ont pour effet de modifier la position courante (bien lire la documentation
 * de chaque methode).
 * </p>
 *
 *  <u>Exemple</u> :
 * <pre>
 * //Creation d'une liste vide
 * IListeTriee&lt;Integer&gt; liste = new ListeTriee&lt;&gt; ();
 *
 * //Ajout de 6 elements
 * liste.ajouter(17);
 * liste.ajouter(3);
 * liste.ajouter(8);
 * liste.ajouter(23);
 * liste.ajouter(9);
 * liste.ajouter(11);
 *
 * //Ici, liste = [3, 8, 9, 11, 17, 23] (element courante = 11)
 *
 * //supprimer les nombre impairs
 * liste.debut(); //placer la position courante sur le premier element
 *
 * do {
 *    if (liste.elementCourant() % 2 != 0) {
 *       liste.supprimer();
 *    }
 * } while (liste.suivant());
 *
 * //Ici, liste = [8] (element courant = 8)
 * </pre>
 *
 * @author Melanie Lord
 * @version 02/02/2018
 * @param <T> le type des elements dans cette liste triee.
 */
public interface IListeTriee <T extends Comparable>  {

    /**
     * <p>Ajoute l'element donne dans cette liste (si possible), en respectant
     * l'ordre croissant des elements. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si element est null, l'ajout n'est pas effectue, et la methode leve une
     * exception NullPointerException.</li>
     *
     * <li>Si element n'est pas null et qu'il existe deja dans cette liste, l'ajout
     * n'est pas effectue  (doublons interdits), et la methode retourne false
     * pour le signaler.</li>
     *
     * <li>Si l'ajout est effectue, la methode retourne true, et l'element courant
     * devient l'element ajoute (la position courante est deplacee sur l'element
     * ajoute).</li>
     * </ul>
     *
     * @param element l'element a ajouter dans cette liste.
     * @return true si l'ajout est effectue, false sinon.
     * @throws NullPointerException si element est null.
     */
    public boolean ajouter (T element);

    /**
     * <p>Ajoute les elements de autreListe dans cette liste, en conservant l'ordre
     * croissant. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si autreListe est null, cette liste n'est pas modifiee, et la methode leve
     * une exception NullPointerException.</li>
     *
     * <li>Lorsqu'un element de autreListe existe deja dans cette liste,
     * celui-ci n'est pas ajoute (doublons interdits).</li>
     *
     * <li>Apres l'appel de cette methode, si cette liste n'est pas vide,
     * la position courante est placee sur le premier element de cette liste
     * (le premier element devient l'element courant).</li>
     *
     * <li>Apres l'appel de cette methode, les elements de autreListe n'ont pas
     * ete modifies, mais la position courante peut avoir ete modifiee.</li>
     * </ul>
     * <pre>
     * Exemple : soit cette liste = [4, 5, 7, 10, 11, 23], et autreListe = [3, 6, 7, 9],
     *           apres l'appel ajouter(autreListe) :
     *           cette liste = [3, 4, 5, 6, 7, 9, 10, 11, 23] (element courant = 3).
     * </pre>
     *
     * @param autreListe la liste contenant les elements a ajouter dans cette liste.
     * @return le nombre d'elements ajoutes dans cette liste (0 si aucun).
     * @throws NullPointerException si autreListe est null.
     */
    public int ajouter (IListeTriee<T> autreListe);

    /**
     * <p>Permet de consulter l'element qui se trouve a la position courante de
     * cette liste (l'element courant), si celle-ci n'est pas vide. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si cette liste est vide, la methode leve une exception
     * ListeVideException.</li>
     * <li>Cette methode ne modifie pas cette liste (incluant sa position
     * courante).</li>
     * </ul>
     *
     * @return l'element a la position courante (element courant), dans cette liste.
     * @throws ListeVideException si cette liste est vide.
     */
    public T elementCourant();

    /**
     * <p>Deplace la position courante sur l'element donne, si celui-ci existe
     * dans cette liste. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si la liste est vide, la position courante de cette liste demeure inchangee,
     * et la methode leve une exception ListeVideException.</li>
     *
     * <li>Si la liste n'est pas vide, mais que l'element donne est null, la position
     * courante demeure inchangee, et la methode leve une exception
     * NullPointerException.</li>
     *
     * <li>Si la liste n'est pas vide, mais que l'element (non null) donne n'existe
     * pas dans cette liste, la position courante demeure inchangee, et la
     * methode leve une exception NoSuchElementException.</li>
     * </ul>
     *
     * @param element l'element sur lequel deplacer la position courante.
     * @throws ListeVideException si cette liste est vide.
     * @throws NullPointerException si l'element donne est null.
     * @throws NoSuchElementException si l'element donne (non null) n'existe
     *         pas dans cette liste.
     */
    public void positionner (T element);

    /**
     * <p>Deplace la position courante sur l'element se trouvant au debut de cette
     * liste (le premier element devient l'element courant), si cette liste n'est
     * pas vide.</p>
     *
     * @throws ListeVideException si cette liste est vide.
     */
    public void debut ();

    /**
     * <p>Deplace la position courante sur l'element se trouvant a la fin de cette
     * liste (son dernier element devient l'element courant), si cette liste n'est
     * pas vide.</p>
     *
     * @throws ListeVideException si cette liste est vide.
     */
    public void fin ();

    /**
     * <p>Deplace la position courante sur l'element qui precede l'element courant
     * dans cette liste. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si cette liste est vide, aucune modification n'est effectuee, et la methode
     * leve une exception ListeVideException.</li>
     *
     * <li>S'il n'y a pas de precedent parce que l'element courant est l'element
     * en debut de liste (avant l'appel de cette methode), la position
     * courante demeure inchangee, et la methode retourne false pour signaler
     * qu'on est au debut de la liste.</li>
     *
     * <li>S'il y a un precedent, la methode retourne true pour signaler que
     * l'operation a bien ete effectuee.</li>
     * </ul>
     *
     * @return true si l'operation a ete effectuee, false sinon.
     * @throws ListeVideException si cette liste est vide.
     */
    public boolean precedent ();

    /**
     * <p>Deplace la position courante sur l'element qui suit (vient apres)
     * l'element courant dans cette liste. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si la liste est vide, aucune modification n'est effectuee, et la methode
     * leve une exception ListeVideException.</li>
     *
     * <li>S'il n'y a pas de suivant parce que l'element courant est l'element
     * en fin de liste (avant l'appel de cette methode), la position
     * courante demeure inchangee, et la methode retourne false pour signaler
     * qu'on est a la fin de la liste.</li>
     *
     * <li>S'il y a un suivant, la methode retourne true pour signaler que l'operation
     * a bien ete effectuee.</li>
     * </ul>
     * @return true si l'operation a ete effectuee, false sinon.
     * @throws ListeVideException si cette liste est vide.
     */
    public boolean suivant ();

    /**
     * <p>Supprime l'element courant de cette liste, et retourne l'element supprime.
     * </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si cette liste est vide avant la suppression, celle-ci n'est pas modifiee
     * et la methode leve une exception ListeVideException.</li>
     * <li>Si la suppression est effectuee, et que cette liste n'est pas vide apres
     * la suppression :
     *       <ul>
     *       <li>Si l'element supprime etait le premier element de cette liste, la
     *       position courante est placee sur le (nouveau) premier element de cette liste.</li>
     *
     *       <li>Sinon, la position courante est placee sur l'element qui precedait
     *       l'element supprime dans cette liste.</li>
     *       </ul>
     * </li>
     *
     * </ul>
     * @return l'element supprime de cette liste.
     * @throws ListeVideException si cette liste est vide avant l'appel.
     */
    public T supprimer ();

    /**
     * <p>Supprime l'element donne de cette liste. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si cette liste est vide avant la suppression, celle-ce n'est pas modifiee
     * et la methode leve une exception ListeVideException.</li>
     *
     * <li>Si cette liste n'est pas vide, et que l'element donne est null, la methode
     * leve une exception NullPointerException.</li>
     *
     * <li>Si element (non null) n'est pas dans cette liste, la suppression n'est
     * pas effectuee, la position courante n'est pas modifiee, et la methode
     * retourne false pour signaler que l'operation n'a pas ete effectuee.</li>
     *
     * <li>Si la suppression est effectuee et que cette liste n'est pas vide apres la
     * suppression :
     *
     *       <ul><li>Si l'element supprime etait le premier element de cette liste, la
     *       position courante est placee sur le (nouveau) premier element de cette
     *       liste.</li>
     *
     *       <li>Sinon, la position courante est placee sur l'element qui precedait
     *       l'element supprime dans cette liste.</li>
     *       </ul></li>
     * </ul>
     *
     * <pre>
     * Exemple 1 : Soit cette liste = [1, 3, 6, 7, 11, 23, 42, 100]
     *             Apres l'appel de supprimer(7)
     *             cette liste = [1, 3, 6, 11, 23, 42, 100] et element courant = 6.
     *
     * Exemple 2 : Soit cette liste = [1, 3, 6, 7, 11, 23, 42, 100]
     *             Apres l'appel de supprimer(100)
     *             cette liste = [1, 3, 6, 11, 23, 42] et element courant = 42.
     *
     * Exemple 3 : Soit cette liste = [1, 3, 6, 7, 11, 23, 42, 100]
     *             Apres l'appel de supprimer(1)
     *             cette liste = [3, 6, 11, 23, 42] et element courant = 3.
     *
     * Exemple 4 : Soit cette liste = [1]
     *             Apres l'appel de supprimer(1)
     *             cette liste = [] (liste vide) et aucun element courant.
     * </pre>
     * @param element l'element a supprimer.
     * @return true si l'element a ete supprime, false sinon.
     * @throws ListeVideException si cette liste est vide avant l'appel.
     * @throws NullPointerException si element est null.
     */
    public boolean supprimer (T element);

    /**
     * <p>Supprime de cette liste, les elements contenus dans autreListe, lorsque
     * possible. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si autreListe est null, la methode leve une exception
     * NullPointerException.</li>
     *
     * <li>Lorsqu'un element de autreListe n'existe pas dans cette liste,
     * celui-ci n'est (evidemment) pas supprime de cette liste.</li>
     *
     * <li>Si cette liste n'est pas vide apres l'appel de cette methode, sa
     * position courante est placee sur son premier element (le plus petit).</li>
     *
     * <li>Si la liste retournee n'est pas vide, sa position courante est placee
     * sur son premier element (le plus petit).</li>
     *
     * <li>Apres l'appel de cette methode, les elements de autreListe n'ont pas
     * ete modifies, mais la position courante peut avoir ete modifiee.</li>
     * </ul>
     *
     * @param autreListe la liste contenant les elements qu'on veut supprimer
     *        de cette liste.
     * @return une liste de tous les elements qui ont ete supprimes. Si
     *         aucun element n'a ete supprime, la liste retournee est vide.
     * @throws NullPointerException si autreListe est null.
     */
    public IListeTriee<T> supprimer (IListeTriee<T> autreListe);

    /**
     * <p>Retourne true si element est present dans cette liste, false sinon. </p>
     * @param element l'element dont on veut tester l'existence.
     * @return true si element est present dans cette liste, false sinon.
     */
    public boolean elementExiste (T element);

    /**
     * <p>Retourne le nombre d'elements dans cette liste. </p>
     * @return le nombre d'elements dans cette liste.
     */
    public int nbrElements ();

    /**
     * <p>Retourne true si cette liste est vide, false sinon.</p>
     * @return true si cette liste est vide, false sinon.
     */
    public boolean estVide();

    /**
     * <p>Retourne une nouvelle liste qui contient tous les elements de cette liste
     * compris entre elementDebut et elementFin inclusivement. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si cette liste est vide, la methode leve une exception
     * ListeVideException.</li>
     *
     * <li>Si cette liste n'est pas vide, et que elementDebut ou elementFin est null,
     * la methode leve une exception NullPointerException.</li>
     *
     * <li>Si cette liste n'est pas vide et que elementDebut (non null) est
     * strictement plus grand que elementFin (non null), la methode leve une
     * exception NoSuchElementException.</li>
     *
     * <li>Si la sous-liste retournee n'est pas vide, sa position courante est placee
     * sur son premier element (le plus petit).</li>
     *
     * <li>Cette methode ne modifie pas cette liste (incluant sa position courante).</li>
     * </ul>
     *
     * <pre>
     * Exemples : Soit cette liste = [2, 3, 6, 9, 11, 14, 16].
     *              sousListe(2, 11) retourne [2, 3, 6, 9, 11] (elem courant = 2)
     *              sousListe(6, 6) retourne [6] (elem courant = 6)
     *              sousListe(7, 7) retourne [] (liste vide)
     *              sousListe(4, 15) retourne [6, 9, 11, 14] (elem courant = 6 )
     *              sousListe(17, 32) retourne [] (liste vide)
     *              sousListe(4, 5) retourne [] (liste vide)
     * </pre>
     * @param elementDebut l'element qui est la borne inferieure incluse de la
     *                     sous-liste a retourner.
     * @param elementFin l'element qui est la borne superieure incluse de la
     *                   sous-liste a retourner.
     * @return une sous-liste contenant les elements de cette liste allant de
     *         elementDebut a elementFin (inclusivement).
     * @throws ListeVideException si cette liste est vide.
     * @throws NullPointerException si elementDebut ou elementFin est null.
     * @throws NoSuchElementException si elementDebut est plus grand que elementFin.
     */
    public IListeTriee<T> sousListe (T elementDebut, T elementFin);

    /**
     * <p>Retourne une liste des elements qui se trouvent a la fois dans cette liste
     * et dans autreListe. </p>
     *
     * <u>Precisions</u> :
     * <ul>
     * <li>Si autreListe est null, la methode retourne une exception
     * NullPointerException.</li>
     *
     * <li>Cette liste demeure inchangee (incluant sa position courante).</li>
     *
     * <li>Les elements de autreListe demeurent inchanges, mais sa position courante
     * peut avoir ete modifiee.</li>
     *
     * <li>Si la liste retournee n'est pas vide, sa position courante est placee
     * sur son premier element.</li>
     * </ul>
     *
     * @param autreListe la liste a comparer avec cette liste pour trouver les
     *        elements communs de ces deux listes.
     * @return une liste des elements communs entre cette liste et autreListe.
     * @throws NullPointerException si autreListe est null.
     */
    public IListeTriee<T> elementsCommuns (IListeTriee<T> autreListe);

    /**
     * <p>Retire tous les elements de cette liste. Apres l'appel de cette
     * methode, la liste est vide.</p>
     */
    public void vider ();

}
