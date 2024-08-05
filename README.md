Le projet est une application Java de gestion de produits. Voici une description des principales fonctionnalités et composants :

### Fonctionnalités Principales
1. **Ajouter un produit** : Permet d'ajouter un nouveau produit au magasin.
2. **Supprimer un produit** : Permet de supprimer un produit existant en utilisant son ID.
3. **Modifier un produit** : Permet de modifier les détails d'un produit existant.
4. **Rechercher un produit par nom** : Permet de rechercher un produit en utilisant son nom.
5. **Lister les produits par lettre** : Affiche les produits dont le nom commence par une lettre spécifique.
6. **Afficher le nombre de produits en stock** : Affiche le nombre total de produits disponibles en stock.
7. **Exporter les produits en CSV** : Exporte les détails des produits dans un fichier CSV.
8. **Afficher tous les produits** : Affiche tous les produits disponibles dans le magasin.

### Composants Principaux
1. **Main.java** : Contient la logique principale de l'application, y compris le menu de navigation et les appels aux différentes fonctionnalités.
2. **Produit.java** : Classe de base représentant un produit avec des attributs comme `id`, `nom`, `prix`, `quantite`, et `type`.
3. **Vestimentaire.java** : Classe dérivée de `Produit` représentant un produit vestimentaire avec un attribut supplémentaire `taille`.
4. **Alimentaire.java** : Classe dérivée de `Produit` représentant un produit alimentaire avec un attribut supplémentaire `dateExpiration`.

### Structure des Classes
- **Produit** : Classe de base avec des méthodes pour obtenir et définir les attributs de base d'un produit.
- **Vestimentaire** : Hérite de `Produit` et ajoute un attribut spécifique `taille`.
- **Alimentaire** : Hérite de `Produit` et ajoute un attribut spécifique `dateExpiration`.

### Utilisation
L'application utilise un scanner pour lire les entrées de l'utilisateur et exécuter les différentes fonctionnalités en fonction des choix de l'utilisateur. Les produits peuvent être ajoutés, modifiés, supprimés, recherchés, listés, et exportés en CSV.![Capture d’écran du 2024-08-05 22-43-30](https://github.com/user-attachments/assets/fde1017c-7676-4ff3-a176-e6ed70d98270)
![Capture d’écran du 2024-08-05 22-43-12](https://github.com/user-attachments/assets/c1aeabc2-1b68-4d50-98a9-8f2fc4648034)
![Capture d’écran du 2024-08-05 22-43-02](https://github.com/user-attachments/assets/b587bafe-5bd2-4a81-82f6-e8ec1d377929)
![Capture d’écran du 2024-08-05 22-42-43](https://github.com/user-attachments/assets/a985b395-2467-456e-b712-ffc06e8d74ac)
![Capture d’écran du 2024-08-05 22-41-41](https://github.com/user-attachments/assets/f5c22dae-c031-4adf-b89c-078bd77a001e)
![Capture d’écran du 2024-08-05 22-40-25](https://github.com/user-attachments/assets/6fb79fc7-e4f7-4cd1-9ec7-1c9156041d8b)
![Capture d’écran du 2024-08-05 22-40-14](https://github.com/user-attachments/assets/43c81fa0-f257-4fa7-9f8a-1697ac5ae919)
![Capture d’écran du 2024-08-05 22-39-39](https://github.com/user-attachments/assets/bc020f4e-d849-43a8-8eb8-b42b62fa745b)
![Capture d’écran du 2024-08-05 22-38-58](https://github.com/user-attachments/assets/7fdd88b6-0d2a-4d80-a191-713c0f146a75)
