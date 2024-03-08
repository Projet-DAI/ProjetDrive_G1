package emailGenerator;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMyEmail {
	// Boîte aux lettres et mot de passe de l'expéditeur (à remplacer par votre propre boîte aux lettres et votre propre mot de passe)
	// PS : Certains serveurs de boîtes aux lettres définissent un mot de passe distinct pour les clients SMTP afin d'accroître la sécurité du mot de passe de la boîte aux lettres (certaines boîtes aux lettres sont appelées "code d'autorisation"), // pour les boîtes aux lettres avec mot de passe distinct, le mot de passe de la boîte aux lettres ici doit utiliser ce mot de passe distinct (code d'autorisation).
	// Pour les boîtes aux lettres avec mot de passe indépendant, le mot de passe de la boîte aux lettres doit utiliser ce mot de passe indépendant (code d'autorisation).
    public static String myEmailAccount = "panghanfr@gmail.com";
    public static String myEmailPassword = "nhjc uwtj hwqq zcin";

	 // L'adresse du serveur SMTP de la boîte aux lettres de l'expéditeur doit être exacte, une adresse de serveur de messagerie différente est différente, format général (juste général, jamais absolu) : smtp.xxx.com
	 // L'adresse du serveur SMTP de la boîte aux lettres gmail est : smtp.gmail.com.
    public static String myEmailSMTPHost = "smtp.gmail.com";

    // Courriel du destinataire (remplacer par un courriel valide que vous connaissez)
    public static String receiveMailAccount = "mazhuo0825@gmail.com";
    
    
    
    public static String getMyEmailAccount() {
		return myEmailAccount;
	}



	public static void setMyEmailAccount(String myEmailAccount) {
		SendMyEmail.myEmailAccount = myEmailAccount;
	}



	public static String getMyEmailPassword() {
		return myEmailPassword;
	}



	public static void setMyEmailPassword(String myEmailPassword) {
		SendMyEmail.myEmailPassword = myEmailPassword;
	}



	public static String getMyEmailSMTPHost() {
		return myEmailSMTPHost;
	}



	public static void setMyEmailSMTPHost(String myEmailSMTPHost) {
		SendMyEmail.myEmailSMTPHost = myEmailSMTPHost;
	}



	public static String getReceiveMailAccount() {
		return receiveMailAccount;
	}



	public static void setReceiveMailAccount(String receiveMailAccount) {
		SendMyEmail.receiveMailAccount = receiveMailAccount;
	}



	/**
     * Créer un simple courriel
     *
     * @param receiveMail
     * @return
     * @throws Exception
     */
    public static void createMimeMessage(String receiveMail, String pdfPath, String nomU, String idC) throws Exception {
    	// Initialisation
    	// Créer une configuration de paramètres pour la connexion au serveur de messagerie.
        Properties props = new Properties();                    // Configuration des paramètres
        props.setProperty("mail.transport.protocol", "smtp");   // Protocole utilisé (requis par la spécification JavaMail)
        props.setProperty("mail.smtp.host", SendMyEmail.myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // Nécessité de demander une authentification

        // PS : Certains serveurs de messagerie exigent la sécurité SSL pour les connexions SMTP (pour plus de sécurité, les boîtes aux lettres prennent en charge les connexions SSL, ou vous pouvez l'activer vous-même), // Si vous ne pouvez pas vous connecter au serveur de messagerie, regardez le journal imprimé sur la console, s'il y a des erreurs comme "connexion échouée, connexion sécurisée SSL requise", etc.
        // Si vous ne pouvez pas vous connecter au serveur de messagerie, vérifiez le journal imprimé sur la console, s'il y a une erreur comme "Connection failed, SSL secure connection required", // alors vous pouvez l'utiliser pour vous connecter au serveur de messagerie.
        // Décommentez ce qui suit /* ... */ ci-dessous, et activez la connexion sécurisée SSL.
        
        // Le port du serveur SMTP (les ports non-SSL sont généralement 25 par défaut et peuvent être laissés de côté, mais si SSL est activé, // vous devez le remplacer par le port SMTP de la boîte aux lettres correspondante.
        // Le port du serveur SMTP de la boîte aux lettres QQ est 25 par défaut, vous ne pouvez pas l'ajouter, si vous ouvrez une connexion SSL.
        // Le port SMTP (SLL) de la boîte aux lettres QQ est 465 ou 587, les autres boîtes aux lettres peuvent le vérifier elles-mêmes).
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // Créer un objet session basé sur la configuration, qui est utilisé pour interagir avec le serveur de messagerie.
        Session session = Session.getInstance(props);
        // debug true
        session.setDebug(true);

    	
    	// creer un email
        MimeMessage message = new MimeMessage(session);

        // 2.De : (email header)
        message.setFrom(new InternetAddress(SendMyEmail.myEmailAccount, "FRESH Magasin", "UTF-8"));

        // 3. To : Destinataire (plusieurs destinataires, cc, bcc peuvent être ajoutés)
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, nomU, "UTF-8"));

        // 4 Objet : Objet du message
        message.setSubject("Votre commande No." + idC + " est Prête à retirer dans votre magasin !", "UTF-8");
        
        // 5. contenu : le corps du message (des balises html peuvent être utilisées)
        MimeMultipart multipart = new MimeMultipart();

        // texte
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent("Chère client(e), <br><br>Ne oubliez pas à retirer votre commande ! <br><br>Bonne journée,<br>Team FRESH", "text/html;charset=UTF-8");
        multipart.addBodyPart(textPart);

        // Annexes
        MimeBodyPart pdfPart = new MimeBodyPart();
        DataSource source = new FileDataSource(pdfPath); // Remplacer par le chemin d'accès au fichier PDF
        pdfPart.setDataHandler(new DataHandler(source));
        
        String fileName = pdfPath.substring(4, pdfPath.length());
        pdfPart.setFileName(fileName); // Définition du nom du fichier de la pièce jointe
        multipart.addBodyPart(pdfPart);

     	// Définition du contenu du message
        message.setContent(multipart);

        // 6. réglage de l'heure d'envoi
        message.setSentDate(new Date());

        // Sauvegarder les paramètres
        message.saveChanges();
        
        // Obtenir l'objet de transport du courrier en fonction de la session
        Transport transport = session.getTransport();

        // Le courriel d'authentification doit être le même que le courriel de l'expéditeur dans le message, sinon une erreur sera signalée.
        //
        // PS_01 : Si la connexion au serveur échoue, un log de la raison de l'échec sera affiché sur la console.
        // Certains serveurs de messagerie renvoient un code d'erreur ou un lien permettant d'afficher le type d'erreur.
        // Selon le type d'erreur indiqué, consultez le site d'aide du serveur de messagerie correspondant pour connaître la raison exacte de l'échec.
        // Le type d'erreur sera indiqué sur le site d'aide du serveur de messagerie correspondant.
        // PS_02 : Les connexions échouent généralement pour les raisons suivantes, vérifiez attentivement le code.
        // (1) Le service SMTP n'est pas activé dans la boîte aux lettres ; // (2) Le mot de passe de la boîte aux lettres est incorrect.
        // (2) Le mot de passe de la boîte aux lettres est erroné, par exemple certaines boîtes aux lettres ont des mots de passe individuels activés.
        // (3) Le serveur de la boîte aux lettres nécessite une connexion SSL sécurisée ; // (4) La demande est trop fréquente ; // (5) Le serveur de la boîte aux lettres nécessite une connexion SSL sécurisée ; // (6) Le serveur de la boîte aux lettres nécessite une connexion SSL sécurisée ; // (7) Le serveur de la boîte aux lettres nécessite une connexion SSL sécurisée.
        // (4) Les demandes sont trop fréquentes ou pour d'autres raisons, le serveur de messagerie refuse le service ; // (5) Si l'une des conditions ci-dessus s'applique, le serveur de messagerie n'acceptera pas la demande.
        // (5) Si vous êtes sûr que tous les points ci-dessus sont corrects, consultez le site web du serveur de messagerie pour obtenir de l'aide.
        //
        transport.connect(myEmailAccount, SendMyEmail.myEmailPassword);

        // 6. Envoyer email
        transport.sendMessage(message, message.getAllRecipients());

        // 7. close
        transport.close();

    }
    
    

    public static void main(String[] args) throws Exception {
    

        
    }

    
}

