package com.uab.sante.service;

import com.uab.sante.entities.*;
import com.uab.sante.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

import java.util.List;
import java.util.Properties;


@Service
public class SouscriptionService {


    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private DetailsCreditRepository detailsCreditRepository;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private DetailsCreditService detailsCreditService;
    @Autowired
    private SouscriptionRepository souscriptionRepository;
    @Autowired
    private QuestionnaireMedicalRepository questionnaireMedicalRepository;
    @Autowired
    private MandataireRepository mandataireRepository;
    @Autowired
    private InformationEmploiRepository informationEmploiRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SouscriptionService.class);

    public static final String GREETING = "tif@8899";
    // Déclaration d'une constante de type char
    public static char[] NEW_LINE = {'\n'};

    public Souscription save(Souscription souscription) throws MessagingException {

        Personne personne = souscription.getPersonne();
        DetailsCredit detailsCredit = souscription.getDetailsCredit();
        QuestionnaireMedical questionnaireMedical = souscription.getQuestionnaireMedical();
        Mandataire mandataire = souscription.getMandataire();
        InformationEmploi informationEmploi = souscription.getInformationEmploi();

        // Enregistrer la personne et les détails du crédit
        personne = personneRepository.save(personne);
        detailsCredit = detailsCreditRepository.save(detailsCredit);
        questionnaireMedical = questionnaireMedicalRepository.save(questionnaireMedical);
        mandataire = mandataireRepository.save(mandataire);
        informationEmploi = informationEmploiRepository.save(informationEmploi);

        // Mettre à jour la souscription avec les références aux enregistrements nouvellement créés
        souscription.setPersonne(personne);
        souscription.setDetailsCredit(detailsCredit);
        souscription.setQuestionnaireMedical(questionnaireMedical);
        souscription.setMandataire(mandataire);
        souscription.setInformationEmploi(informationEmploi);

        // Enregistrer la souscription complète
        //sendEmail1();
        return souscriptionRepository.save(souscription);

    }

    public Souscription getSouscriptionById(Long id) {
        return souscriptionRepository.findById(id).orElse(null);
    }

        public List<Souscription> findAll(){
        List<Souscription> souscriptions = souscriptionRepository.findAll();
        logger.info("========== List souscriptions en cours dans le service traiter ===============");
        System.out.println("=====================    souscriptionsList service   ========================");
        System.out.println(souscriptions);
        System.out.println("=====================    souscriptionsList service   ========================");
        return souscriptions;
    }



    public void sendEmail(String to, String subject, String body) throws MessagingException {
        // Configuration des propriétés pour la connexion SMTP

        System.out.println("=====================    sendEmail   ========================");
        System.out.println(to);
        System.out.println(subject);
        System.out.println(body);
        System.out.println("=====================    sendEmail   ========================");
        Properties props = new Properties();
        Properties prop = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Serveur SMTP de Gmail
        props.put("mail.smtp.port", "587"); // Port SMTP pour TLS
        props.put("mail.smtp.auth", "true"); // Activer l'authentification SMTP
        props.put("mail.smtp.starttls.enable", "true"); // Activer le démarrage TLS

        String username = "faycaltifa@gmail.com";
        String password = "tifa8899";
        String password1 = Arrays.toString("tifa@8899".toCharArray());
        String str = "Hello";
        char[] charArray = str.toCharArray();
        // Création d'une session SMTP avec authentification

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Adresse expéditeur Gmail
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Adresse destinataire
            message.setSubject(subject); // Sujet du message
            message.setText(body); // Corps du message

            // Envoi du message
            Transport.send(message);
            System.out.println("=====================    sendEmail OKKKKK  ========================");
            System.out.println("E-mail envoyé avec succès !");
            System.out.println("=====================    sendEmail OKKKKK  ========================");
        } catch (MessagingException e) {
            System.out.println("=====================    sendEmail ECHEC  ========================");
            throw new RuntimeException("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }



    public void sendEmail1() {

        final String username = "faycaltifa@gmail.com";
        final String password = "tifa8899";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("faycaltifa@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("faycal1er@yahoo.fr")
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
