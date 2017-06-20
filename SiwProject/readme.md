Progetto realizzato per il corso di Sistemi Informativi su Web tenuto dal Professor Paolo Merialdo.

Possibili casi d'uso:

UC1
1.Un utente vuole registrarsi sul sito.
2.L'utente accede alla pagina di registrazione tramite il pulsante "registrati!" posto in alto a destra su ogni pagina del sito.
3.L'utente inserisce i dati personali e preme il pulsante "Registrati!".
4.Il sistema registra l'utente salvando i suoi dati nel db.

UC2
1.Un utente vuole accedere al sito.
2.L'utente preme il pulsante "login" posto in alto a destra su ogni pagina del sito.
3.L'utente inserisci il suo username e la sua password e preme "Login".
4.Il sistema controlla i dati inseriti e fa accedere l'utente.

Variante UC2
2a.L'utente preme il pulsante "Login with Facebook".
3a.L'utente accede a Facebook utilizzando le sue credenziali.
4a.Il sistema controlla l'avvenuto accesso a facebook, controlla se la email esiste nel database ed in caso non sia presente procede con la creazione dell'account nel database, dopodichè fa accedere l'utente.

UC3
1.L'utente vuole inserire una foto.
2.L'utente una volta autenticato l'utente procede premendo il pulsante "Upload nuove foto".
3.L'utente inserisce una descrizione e carica la foto.
4.L'utente preme il pulsante "Aggiungi Foto" ed il sistema si prende carico di caricare la foto.

UC4
1.L'utente vuole eliminare una foto.
2.L'utente una volta autenticato procede premendo il pulsante "Vedi le tue foto".
3.Una volta individuata la foto da cancellare l'utente preme il pulsante "Elimina" posto subito sotto la foto da eliminare.
3.Il sistema procede ad eliminare la foto.


UC5
1.L'utente vuole aggiornare l'Avatar.
2.L'utente una volta autenticato l'utente procede premendo il pulsante "Aggiorna Avatar".
3.L'utente carica la foto.
4.L'utente preme il pulsante "Aggiungi Avatar" ed il sistema si prende carico di caricare la foto ed aggiornare l'avatar corrente dell'utente.

UC6
Un utente può mettere "mi piace" alle foto di altri utenti e può visualizzare le foto di altri utenti. Può inoltre visualizzare il profilo di un utente, mandargli una mail (una volta effettuato l'accesso al sito) ed accedere ai suoi profilo social (se presenti).

UC7
Un amministratore del sistema può eliminare un utente dal sistema in qualsiasi momento premendo il pulsante "Elimina Utente" ed il sistema effettuerà la sua cancellazione comprese tutte le sue foto caricate.


Class Diagram


__________
| Ruolo	 |
|________|
|	     |||________|  
	 1^|
      | 
      |
      ||
-----------------						  	--------------
|	  Utente     |		              Foto 	|   Foto	|o
|----------------| ----------------------->*|-----------|-
|				 |				FotoVotate	|        	||
|				 | ----------------------->*|			|		|
|				 | 	Proprietario			|           |
					
|			     |	1<----------------------|			||
| 		 		 |							|			|
 					UtentiVotanti
|----------------|	*<----------------------|-----------||i








