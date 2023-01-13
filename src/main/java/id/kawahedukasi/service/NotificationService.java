package id.kawahedukasi.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;

@ApplicationScoped
public class NotificationService {

    //inject mailer class untuk bisa digunakan
    @Inject
    Mailer mailer;

    public void sendEmail(){
         mailer.send(
                 Mail.withText("anandapratama03@gmail.com",
                         "Test From CRUD API Quarkus",
                         "Hello, this is Quarkus"));
    }

    public void sendEmailWithAttachment(){
        mailer.send(
                Mail.withText("anandapratama03@gmail.com",
                        "Attachment CRUD API Quarkus",
                        "This is you attachment")
                        .addAttachment("identity-card.jpeg",
                                new File("C:\\Users\\Dana\\Downloads\\ktp-dummy.jpg"),
                                "image/jpeg")
        );
    }

    public void sendEmailWithHtml(){
        mailer.send(
                Mail.withHtml("anandapratama03@gmail.com",
                "HTML CRUD API Quarkus",
                "<h1>JUDUL BESAR</h1> \n" +
                    "<p>Salam dari binjai</p>")
        );
    }
}
