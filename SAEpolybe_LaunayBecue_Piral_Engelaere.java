class SAEpolybe_LaunayBecue_Piral_Engelaere extends Program {

    final int LARGEUR = 5;  // taille du carré (5x5 dans notre cas)

    //////////////////////////////////////////////////////////////////////////

    // La fonction String initialiserCarre() retourne une chaine de caractères contenant le carré de Polybe (version de base, sans clé, c'est-à-dire la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ", le V et le W sont "fusionnés" en V)
    String initialiserCarreSimple(){
        String carre = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        return carre;
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction void afficherCarre(String carre) affiche le carré de Polybe carrz passé en paramètre comme illustré dans l'exemple ci-après.
    // Par exemple : si le carré passé en paramètre est : "ABCDEFGHIJKLMNOPQRSTUVXYZ", la fonction devra afficher :
    //  |0 1 2 3 4
    // ------------
    // 0|A B C D E
    // 1|F G H I J
    // 2|K L M N O
    // 3|P Q R S T
    // 4|U V X Y Z

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    void afficherCarre(String carre){
        println(" |0 1 2 3 4");
        println("------------");
        for (int i = 0; i < LARGEUR; i++) {
            print(i + "|");
            for (int j = 0; j < LARGEUR; j++) {
                print(carre.charAt(i * LARGEUR + j) + " ");
            }
            println();
        }
    }
    //////////////////////////////////////////////////////////////////////////

    // La fonction String coderLettre (String carre, char lettre) retourne une chaîne de 2 caractères (2 entiers entre 0 inclus et LARGEUR exclus) contenant l'encodage du caractère lettre passé en paramètre en considérant le carré de Polybe carre également passé en paramètre.
    // Par exemple :
    // si on considère le carré de Polybe sans clé (càd le carré ABCDE représenté par la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ") :
    //                                                           FGHIJ
    //                                                           KLMNO
    //                                                           PQRST
    //                                                           UVWYZ
    //      'A' est codé "00"
    //      'B' est codé "01"
    //      'F' est codé "10"
    //      'V' est codé "41"
    //      'W' est codé "41"
    //      'Z' est codé "44"
    //      'P' est codé "30"

    // si on considère le carré de Polybe donné par la chaine "AZERTYUIOPQSDFGHJKLMXCVBN" :
    //      'A' est codé "00"
    //      'B' est codé "43"
    //      'Z' est codé "01"

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    // Indication : pensez à la division entière et au modulo

    String coderLettre(String carre, char lettre){
        for (int i = 0; i < carre.length(); i++) {
            if (lettre == 'W') {
                lettre = 'V';
            }
            if (carre.charAt(i) == lettre) {
                return ("" + i / LARGEUR) + "" + (i % LARGEUR);
            }
        }
        return ("error");
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction String coderMessage (String carre, String message) retourne une chaîne de caractères contenant l'encodage de la chaîne de caractères message passé en paramètre avec le carré de Polybe carre donné en paramètre.
    // Chaque paire d'entiers (compris entre 0 et 4) correspondant à chaque lettre sera séparée de la suivante par un espace.
    // Pensez à utiliser la fonction coderLettre.
    // Par exemple, si le carré considéré est celui sans clé ("ABCDEFGHIJKLMNOPQRSTUVXYZ") et le message à coder est "BONJOUR" alors le résultat attendu est "01 24 23 14 24 40 32 "

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message passé en paramètre est valide (càd constitué uniquement des 26 lettres de l'alphabet en majuscule)

    String coderMessage(String carre, String message){
        String msg_coded = "";
        for (int i = 0; i < message.length(); i++) {
            msg_coded += coderLettre(carre, message.charAt(i)) + " ";
        }
        return (msg_coded);
    }
    //////////////////////////////////////////////////////////////////////////

    // La fonction String decoderMessage (String carre, String messageCode) retourne une chaîne de caractères contenant le décodage de la chaîne de caractère messageCode avec le carré de Polybe carre donné en paramètre.
    // Par exemple, si carre = "ABCDEFGHIJKLMNOPQRSTUVXYZ" et messageCode = "01 24 23 14 24 40 32 " alors le résultat attendu est "BONJOUR"

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message codé passé en paramètre est valide (càd constitué de paires d'entiers compris entre 0 et LARGEUR-1 inclus et séparées par un espace)

    String decoderMessage(String carre, String messageCode){
        String msg_decoded = "";
        for (int i = 0; i < messageCode.length(); i += 3) {
            msg_decoded += carre.charAt((messageCode.charAt(i) - '0') * LARGEUR + (messageCode.charAt(i + 1) - '0'));
        }
        return (msg_decoded);
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction boolean estPresent(String mot, char lettre) retourne True si le caractère lettre est dans mot, faux sinon.
    // Par exemple :
    //      si mot = "BONJOUR" et lettre = 'B' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'R' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'M' alors le résultat de la fonction est False

    boolean estPresent(String mot, char lettre){
        for (int i = 0; i < mot.length(); i++) {
            if (mot.charAt(i) == lettre) {
                return (true);
            }
        }
        return (false);
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction String initialiserCarreAvecCle(String cle) retourne une chaine de caractères contenant le carré de Polybe amélioré en considérant la clé passée en paramètre.
    // Pensez à utiliser la fonction estPresent
    // Par exemple, si cle = "BONJOUR" alors le résultat attendu est : "BONJURACDEFGHIKLMPQSTVXYZ"
    // Par exemple, si cle = "BUTINFORMATIQUE" alors le résultat attendu est : "BUTINFORMAQECDGHJKLPSVXYZ"

    // NB : On considère dans cette fonction que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule, le W se substituera en V)

    String initialiserCarreAvecCle(String cle){
        String carre = "";
        for (int i = 0; i < length(cle); i++) {
            char lettre = charAt(cle, i);
            if (lettre == 'W') {
                lettre = 'V';
            }
            if (!estPresent(carre, lettre)) {
                carre += lettre;
            }
        }
        for (int i = 0; i < (50-length(carre)); i++) {
            char lettre = 'A';
            lettre += i;
            if (lettre == 'W') {
                lettre = 'V';
            }
            if (!estPresent(carre, lettre)) {
                carre += lettre;
            }
        }
        return (carre);
    }

    //////////////////////////////////////////////////////////////////////////
    // LES FONCTIONS QUI SUIVENT SONT DES FONCTIONS DE VERIFICATION DE SAISIE
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////

    // La fonction boolean estLettreMajuscule(char c) vérifie le caractère passé en paramètre est une lettre de l'alphabet en majuscule
    // Par exemple :
    //  si c='b', la fonction retourne false
    //  si c='B', la fonction retourne true

    boolean estLettreMajuscule(char c){
        return true;
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction estCleValide vérifie que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule)
    // Par exemple :
    //  si cle="BUTINFORMATIQUE", la fonction retourne true
    //  si cle="BUTINF ORMATIQUE", la fonction retourne false
    //  si cle="BUTINFORMATIQUE!", la fonction retourne false
    //  si cle="ButInformatique", la fonction retourne false

    boolean estCleValide(String cle){
        return true;
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction estChiffreOK vérifie que le chiffre passé en paramètre est valide (càd est un entier compris entre 0 et LARGEUR-1)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estChiffreOK(int chiffre){
        return true;
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction estMessageCodeValide vérifie que le message codé passé en paramètre est valide (càd constituée uniquement de paires d'entiers compris entre 0 et LARGEUR-1 et que chaque paire est séparée de la suivante par un espace, et un espace final)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estMessageCodeValide(String messageCode){
        return true;
    }

    //////////////////////////////////////////////////////////////////////////

    // La fonction estMessageValide vérifie que le message passé en paramètre est valide (càd constitué uniquement de lettres de l'alphabet en majuscule)

    boolean estMessageValide(String message){
        return true;
    }

    //////////////////////////////////////////////////////////////////////////
    // PROGRAMME PRINCIPAL
    //////////////////////////////////////////////////////////////////////////

    // Ecrire un programme principal qui :
    // 1. affiche un message d'introduction à l'utilisateur
    // 2. affiche un message à l'utilisateur demandant s'il veut utiliser une clé ?
    // 3. lit la réponse de l'utilisateur
    // 4. si l'utilisateur a répondu oui, demande et lit la clé souhaitée
    // 5. affiche le carré de Polybe (générique (càd sans clé) ou avec clé selon la réponse précédente de l'utilisateur)
    // 6. tant que la réponse n'est pas 0, affiche un menu et demande à l'utilisateur de saisir un entier (0 ou 1 ou 2 ou 3) pour :
    //              0. QUITTER
    //              1. CODER UN MESSAGE
    //              2. DECODER UN MESSAGE
    //              3. MODIFIER LE MODE AVEC/SANS CLE
    //        puis agit en conséquence.
    // NB : si et tant qu'une saisie de l'utilisateur n'est pas correcte, il faut la redemander (que ce soit pour la clé, le message à coder, le message à décoder ou le choix dans le menu)

    void algorithm(){
        println("SAE1.01 - Le carré de Polybe");
        print(" Message a coder -> ");
        String msg_to_code = readString();
        print(" Carre -> ");
        String carre = initialiserCarreAvecCle(readString());
        String msg_coded = coderMessage(carre, msg_to_code);
        String msg_decoded = decoderMessage(carre, msg_coded);
        println("Message à coder : " + msg_to_code);
        afficherCarre(carre);
        println("Message codé : " + msg_coded);
        println("Message décodé : " + msg_decoded);
    }
    //////////////////////////////////////////////////////////////////////////
}