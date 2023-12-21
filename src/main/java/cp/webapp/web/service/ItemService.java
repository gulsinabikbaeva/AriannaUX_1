package cp.webapp.web.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import cp.webapp.web.exceptions.InvalidCategoryException;
import cp.webapp.web.model.*;
import cp.webapp.web.repository.*;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository ir;

    @Autowired
    private UserRepository ur;

    @Autowired
    private CategoryRepository cr;

    @Autowired
    private RoleRepository rr;

    @Autowired
    private SubCategoryRepository scr;

    @Autowired
    private CustomOrderRepository cor;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() throws IOException {
        if (ur.findAll().size() == 0) {

            rr.save(new Role("ROLE_BUYER"));
            rr.save(new Role("ROLE_SELLER"));

            User admin = new User("admin", encoder.encode("admin"), new Role("ROLE_ADMIN"), "Amministratore", "Unico");
            ur.save(admin);

            User buyer = new User("buyer", encoder.encode("buyer"), new Role("ROLE_BUYER"), "BBbbbbbbbbb", "RRrrrrrrrrrrr");
            buyer.setCountry("France");
            buyer.setTowncity("Paris");
            buyer.setStreet("ygvubk");
            buyer.setHouseNumber("877");
            buyer.setZip("dyfy7");
            buyer.setPhone("5758687");
            ur.save(buyer);

            User buyer2 = new User("buyer2", encoder.encode("buyer2"), new Role("ROLE_BUYER"), "yuecguycj", "cucckk");
            buyer2.setCountry("France");
            buyer2.setTowncity("Paris");
            buyer2.setStreet("ygvubk");
            buyer2.setHouseNumber("877");
            buyer2.setZip("dyfy7");
            buyer2.setPhone("5758687");
            ur.save(buyer2);

            User seller = new User("seller", encoder.encode("seller"), new Role("ROLE_SELLER"), "BBbvgddubbbbbb", "ygvcudgu");
            seller.setCountry("France");
            seller.setTowncity("Paris");
            seller.setStreet("ygvubk");
            seller.setHouseNumber("877");
            seller.setZip("dyfy7");
            seller.setPhone("5758687");
            ur.save(seller);

            User seller2 = new User("seller2", encoder.encode("seller2"), new Role("ROLE_SELLER"), "ygc udc jd", "egceucgjcb");
            seller2.setCountry("France");
            seller2.setTowncity("Paris");
            seller2.setStreet("ygvubk");
            seller2.setHouseNumber("877");
            seller2.setZip("dyfy7");
            seller2.setPhone("5758687");
            ur.save(seller2);

        }

        if (cr.findAll().size() == 0) {
            cr.save(new Category("Anatomia patologica"));
            cr.save(new Category("Anestesia e Rianimazione"));
            cr.save(new Category("Cardiologia"));
            cr.save(new Category("Chirurgia Generale"));
            cr.save(new Category("Dietologia e Nutrizione Clinica"));
            cr.save(new Category("Endocrinologia e Diabetologia"));
            cr.save(new Category("Endoscopia dell’apparato digerente"));
            cr.save(new Category("Geriatria"));
            cr.save(new Category("Laboratorio Analisi"));
            cr.save(new Category("Malattie Infettive e Tropicali"));
            cr.save(new Category("Medicina Interna"));
            cr.save(new Category("Nefrologia e Dialisi"));
            cr.save(new Category("Neurologia"));
            cr.save(new Category("Neuropsichiatria Infantile"));
            cr.save(new Category("Oculistica"));
            cr.save(new Category("Oncologia"));
            cr.save(new Category("Ortopedia Traumatologia"));
            cr.save(new Category("Ostetricia Ginecologia"));
            cr.save(new Category("Otorinolaringoiatria"));
            cr.save(new Category("Pediatria"));
            cr.save(new Category("Radiologia"));
            cr.save(new Category("Radioterapia"));
            cr.save(new Category("Recupero e Rieducazione Funzionale"));
            cr.save(new Category("Servizio Immunoematologia e Trasfusionale"));
            cr.save(new Category("Servizio Psichiatrico di Diagnosi e Cura SPDC"));
            cr.save(new Category("Urologia"));
            Category c1 = cr.save(new Category("Altro"));
            scr.save(new SubCategory("Altro1", c1));
            scr.save(new SubCategory("Altro2", c1));
            scr.save(new SubCategory("Altro3", c1));
        }

        if (ir.findAll().size() == 0) {
            Item item1 = new Item();
            item1.setAuthor(ur.getById("admin"));
            item1.setCategory(cr.getById("Anatomia patologica"));
            item1.setType(ItemType.Offerta);
            item1.setTitle("Anatomia patologica");
            item1.setDescription("Il Servizio effettua esami istologici di campioni bioptici e campioni operatori, esami intraoperatori, esami citologici di prelievi di organi superficiali e profondi, di liquidi, di materiali cervicovaginali e di agoaspirazione, riscontri diagnostici. \n" +
                    "Su tutti i materiali in esame si eseguono anche esami immunoistochimici per la più specifica definizione della eventuale patologia. Sugli agoaspirati si esegue anche assistenza tecnica al prelievo. \n" +
                    "Il Servizio è anche parte attiva nei programmi regionali di screening (Progetto Serena) del carcinoma del collo dell'utero, della mammella, e del colon-retto per tutta la Provincia del VCO e nell’attività dei gruppi di lavoro interdisciplinari dei percorsi diagnostico-terapeutici attivi nell’ASLVCO, in ambito di Rete Oncologica Piemonte e Valle d’Aosta.\n" +
                    "Il Servizio è dotato dal 2011 di programma gestionale in rete con tutti i centri prelievo ASLVCO, che consente trasmissione telematica delle prenotazioni esami e della visualizzazione e stampa referti.\n" +
                    "Il Servizio è dotato dal maggio 2014 di un programma di tracciabilità bidimensionale per l’identificazione costante e completa dei materiali dall’ingresso in Anatomia Patologica alla archiviazione.\n" +
                    "In particolare il Servizio gestisce l’archivio materiali inclusi in paraffina per richieste di consulti ed utilizzo per esami di approfondimento di Biologia molecolare presso l’Anatomia Patologica di Novara.\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Accesso diretto:\n" +
                    "Per tutti gli esami istologici è attivo l’accesso diretto al Servizio di Verbania : dal lunedì al venerdì 8,30-12,30\n" +
                    "Per gli esami citologici, in alternativa all’accesso diretto, è attiva la prenotazione, da effettuare con impegnativa presso gli sportelli del CUP o tramite CUP telefonico (escluso i pazienti provenienti dal CAS)\n" +
                    "\n" +
                    "Ambulatori di agoaspirati:\n" +
                    "- martedì dalle 10,30 alle 11,30, presso la Radiologia dell'Ospedale di Omegna (prenotazione tramite Radiologia)\n" +
                    "- martedì dalle 14,00 alle 15,00 presso l’ambulatorio di Endocrinochirurgia di Verbania (prenotazione tramite Diabetologia )\n" +
                    "- il venerdì dalle 11,00 alle 12,00 presso la Radiologia dell'Ospedale di Verbania (prenotazione tramite la Radiologia)\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "Ospedale di Verbania:\n" +
                    "- i referti di pap-test ambulatoriali e di libera professione, di citologici urine, di agoaspirati, di istologici dell’Endoscopia digestiva, vengono ritirati presso lo sportello Ritiro referti dell'Ufficio Accettazione Ricoveri al Piano terra\n" +
                    "- i referti di istologici consegnati ad accesso diretto in Anatomia Patologica vengono ritirati presso lo sportello della Segreteria di Anatomia Patologica\n" +
                    "\n" +
                    "Ospedale di Domodossola:\n" +
                    "- i referti di pap-test ambulatoriali e di libera professione, e di esami istologici endoscopici eseguiti a Verbania e Omegna da Utenti di Domodossola vengono ritirati presso ambulatorio endoscopia di Domodossola\n" +
                    "- i referti citologici urine si ritirano presso la segreteria del laboratorio analisi di Domodossola\n" +
                    "\n" +
                    "Ospedale di Omegna:\n" +
                    "- i referti di pap-test ambulatoriali e di libera professione, di esami citologici urine e di esami istologici endoscopici eseguiti a Verbania e Domodossola da Utenti di Omegna vengono ritirati presso lo Sportello CUP di Omegna\n" +
                    "\n" +
                    "Direzione Sanitaria Ospedaliera:\n" +
                    "I familiari di persona soggetta a riscontro diagnostico possono ritirare il referto autoptico definitivo dopo 60 giorni dall'esecuzione dell'autopsia, presso la Direzione sanitaria dell’Ospedale dove è avvenuto il decesso\n" +
                    "\n" +
                    "Quando:\n" +
                    "Sportello Segreteria Anatomia Patologica Verbania: dal lunedì al venerdì 8,30-12,30\n" +
                    "Sportello Ufficio Accettazione Verbania: lunedì al venerdì dalle ore 8,30 alle ore 12,30 e dalle ore 13,30 alle ore 16,00\n" +
                    "Sportello Laboratorio Analisi Domodossola: dal lunedì al venerdì 11,00-15,30\n" +
                    "CUP Verbania: dal lunedì al venerdì 8,00-12,35 / 14,05-16,00\n" +
                    "CUP Domodossola: dal lunedì al venerdì 8,00-12,35 / 14,05-16,00\n" +
                    "CUP Omegna: dal lunedì al venerdì 8,00-17,00\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Esami citologici da agoaspirati:\n" +
                    "E’ possibile ritirare i referti a partire dal 3° giorno dal prelievo\n" +
                    "Esami citologici di urine:\n" +
                    "E’ possibile ritirare i referti a partire dal 3° giorno dall’ultima consegna\n" +
                    "Esami istologici in accesso diretto:\n" +
                    "E’ possibile ritirare i referti a partire dal 3° giorno dal prelievo\n" +
                    "Esami istologici di colonscopia:\n" +
                    "E’ possibile ritirare i referti a partire dal 4° giorno dalla colonscopia\n" +
                    "Esami istologici di gastroscopia:\n" +
                    "E’ possibile ritirare i referti a partire dal 5° giorno dalla gastroscopia\n" +
                    "Pap test:\n" +
                    "E’ possibile ritirare i referti a partire dal 5° giorno dalla consegna\n" +
                    "\n" +
                    "\n" +
                    "Note:\n" +
                    "I tempi di consegna dei referti possono variare di 1 giorno in caso di test aggiuntivi necessari per la diagnosi, non prevedibili a priori.\n" +
                    "***FINO A COPERTURA DEI POSTI VACANTI DI ORGANICO MEDICO, I TEMPI RIPORTATI NON POSSONO ESSERE GARANTITI\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "REFERTO IN GIORNATA\n" +
                    "\n" +
                    "\"Referto in giornata\" per agoaspirati o biopsie mammarie e prostatiche che giungano in Anatomia Patologica entro le 12.00.\n" +
                    "\n" +
                    "Gli interessati potranno richiedere il servizio al momento della prenotazione del prelievo (richiedere appuntamento per agoaspirato mammario e biopsia mammaria entro le 11.00; appuntamento per biopsia prostatica entro le 11.00 a Verbania, entro le 10.00 a Domodossola).\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "RICHIESTA VETRINI\n" +
                    "\n" +
                    "Per poter visionare preparati eseguiti presso il Servizio di Anatomia Patologica ASL VCO telefonare alla Segreteria del Servizio (0323-541354) per anticipare verbalmente la richiesta, specificare le generalità della persona e dell'esame di cui si richiedono i preparati in visione e concordare giorno ed ora del ritiro dei preparati, far inviare al Servizio da parte della struttra Ospedaliera o Universitaria che esegue la consulenza sui preparati, fax di richiesta ufficiale, la richiesta ufficiale è condizione obbligatoria per il rilascio dei preparati da parte del Servizio.\n" +
                    "\n" +
                    "I preparati possono essere ritirati dal titolare dell'esame o da un suo delegato.\n" +
                    "\n" +
                    "Al momento del ritiro verranno richiesti e registrati generalità e recapito della persona che prende in consegna i preparati e sottoscrizione di dichiarazione di riconsegna dei preparati al Servizio nei tempi tecnici più rapidi.");
            item1.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item1.setAvailable(true);
            post(item1);

            Item item2 = new Item();
            item2.setAuthor(ur.getById("admin"));
            item2.setCategory(cr.getById("Anestesia e Rianimazione"));
            item2.setType(ItemType.Offerta);
            item2.setTitle("Anestesia e Rianimazione");
            item2.setDescription("Anestesia\n" +
                    "\n" +
                    "Rianimazione\n" +
                    "\n" +
                    "Terapia antalgica\n" +
                    "\n" +
                    "Analgesia del parto\n" +
                    "\n" +
                    "Trasporti assistiti\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "L'Attività anestesiologica è ripartita sulle attività chirurgiche, mentre l'attività ambulatoriale riguarda la Terapia Antalgica  Oncologica e  non.\n" +
                    "\n" +
                    "Per quanto riguarda l’ attività oncologica essa è rivolta sia ai pazienti ricoverati che quelli a domicilio.\n" +
                    "\n" +
                    "In tale ambito, in collaborazione con il polo oncologico, viene svolta l’attività di posizionamento di cateteri vascolari, port e cateteri peridurali.\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Ospedale Castelli Verbania:\n" +
                    "Ambulatorio di Anestesia e Rianimazione - dal lunedì al venerdì dalle ore 9,00 alle ore 12,00\n" +
                    "Ambulatorio di Terapia Antalgica - tutti i giovedì dalle ore 14,30 alle ore 16,30.\n" +
                    "Le prenotazioni possono essere effettuate tramite CUP telefonico al numero 840.709210 da telefono fisso e da cellulare\n" +
                    "Ambulatorio di Scrambler terapia: tutti i pomeriggi dalle ore 14,30 alle ore 17,30\n" +
                    "\n" +
                    "REFERTI\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "immediata");
            item2.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item2.setAvailable(false);
            post(item2);

            Item item3 = new Item();
            item3.setAuthor(ur.getById("admin"));
            item3.setCategory(cr.getById("Cardiologia"));
            item3.setType(ItemType.Offerta);
            item3.setTitle("Cardiologia");
            item3.setDescription("La Struttura Complessa di Cardiologia è diretta dal Primario Dott. Alessandro Lupi ed è costituita da 2 divisioni ospedaliere che svolgono la propria attività nei due Presidi Ospedalieri \"Castelli\" di Verbania e \"San Biagio\" di Domodossola. In ciascun Presidio Ospedaliero è presente una guardia attiva cardiologica H24, un’unità coronarica, un reparto di degenza subintensiva monitorizzata, un Day Hospital per l’esecuzione di cardioversione elettrica elettiva, somministrazione ciclica endovenosa di inotropi. Presente sala di elettrofisiologia per l’impianto di loop recorder, impianto o sostituzione di pacemaker e defibrillatori impiantabili, dispositivo per la modulazione della contrattilità cardiaca (CCM), pacemaker endocavitari wireless (“micra”).\n" +
                    "Presso il Presidio Ospedaliero di Domodossola è inoltre presente una Sala Angiografica per le attività di Emodinamica (coronarografie, cateterismi cardiaci destri e sinistri, interventi di angioplastica coronarica e sui vasi periferici, pericardiocentesi) dotata di console integrata nell’angiografo per l’esecuzione di ecografia intravascolare (IVUS), FFR/iFR, OCT e console mobile per aterectomia rotazionale (Rotablator), Infine sono presenti dispositivi di assistenza meccanica al circolo (contropulsazione aortica, Impella).\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Il Reparto è suddiviso in due entità ben distinte:\n" +
                    "\n" +
                    "Unità Coronarica\n" +
                    "Cardiologia\n" +
                    "L’unità Coronarica, costituita da 4 posti letto, è  destinata alle  cure  intensive  per  le situazioni cardiologiche che necessitano di controllo più assiduo e per tale motivo l’infermiere professionale è sempre presente.\n" +
                    "\n" +
                    "Il Reparto di Cardiologia è destinato a pazienti che non richiedono una assistenza di tipo intensivo oppure che hanno superato la fase acuta della malattia.\n" +
                    "\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "\n" +
                    "Presso entrambi i presidi ospedalieri sono presenti ecocardiografi di ultima generazione con sonde cardiologiche per adulti e pediatriche, sonda per diagnostica ColorDoppler vascolare e sonda transesofagea, un cicloergometro/treadmill per l’esecuzione di test ergometrici, un lettino per l’esecuzione di tilt test ed una postazione di analisi e refertazione di monitoraggi Holter ECG 24h o monitoraggi ambulatoriali della pressione arteriosa delle 24h. All’attività ambulatoriale cardiologica di base accessibile via CUP si affiancano ambulatori specialistici di settore (controllo pace-maker e defibrillatori ed ambulatorio di aritmologia, ambulatorio dello scompenso, ambulatorio della cardiopatia ischemica, ambulatorio delle dislipidemie, ambulatorio di cardio-diabetologia, ambulatorio di cardiologia pediatrica, ambulatorio per la gestione della terapia anticoagulante nel paziente con fibrillazione atriale).\n" +
                    "\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "presso l'ambulatorio dove è stato effettuata la prestazione specialistica\n" +
                    "\n" +
                    "Quando:\n" +
                    "a fine prestazione\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "immediatamente (ad eccezione dell'elettrocardiogramma sec. Holter il quale può essere ritirato dopo circa una settimana)");
            item3.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item3.setAvailable(true);
            post(item3);

            Item item4 = new Item();
            item4.setAuthor(ur.getById("admin"));
            item4.setCategory(cr.getById("Chirurgia Generale"));
            item4.setType(ItemType.Offerta);
            item4.setTitle("Chirurgia Generale");
            item4.setDescription("ATTIVITA’ IN REGIME DI RICOVERO:\n" +
                    "La SOC di Chirurgia Generale dell’Ospedale San Biagio si dedica all’attività chirurgica in elezione ed urgenza mediante ricoveri ordinari in elezione o urgenza.\n" +
                    "\n" +
                    "Chirurgia Mini Invasiva Laparoscopica e tradizionale delle patologie maligne e benigne di colon-retto, stomaco, fegato, pancreas, colecisti e delle vie biliari.\n" +
                    "Chirurgia Mini Invasiva Laparoscopica pelvi perineale e del pavimento pelvico.\n" +
                    "Chirurgia colo-proctologica; trattamento chirurgico delle cisti pilonidali.\n" +
                    "Endocrinochirurgia con particolare riferimento alla patologia benigna e maligna della tiroide, della paratiroide e dei surreni.\n" +
                    "Chirurgia Mini Invasiva laparoscopica e tradizionale della patologia della parete addominale con particolare riferimento patologia erniaria (ernie inguino-femoro-crurale, laparoceli).\n" +
                    "Chirurgia delle lesioni benigne e maligne della pelle con particolare riguardo al trattamento del melanoma inclusa biopsia linonodo sentinella ed eventuale dissezione linfonodale ascellare o inguino-crurale / iliaco-otturatoria.\n" +
                    "Chirurgia Ambulatoriale.\n" +
                    "Attività di Pre-ricovero per la Chirurgia in elezione.\n" +
                    "Servizio di Reperibilità per le Urgenze/Emergenze Chirurgiche afferenti al DEA (Dipartimento di Emergenza e Accettazione).\n" +
                    "\n" +
                    "ATTIVITA’ AMBULATORIALE\n" +
                    "• Visite di Chirurgia Generale\n" +
                    "• Visita di controllo e medicazioni post-ricovero\n" +
                    "• Visite per Patologia Pelvi perineale e pavimento pelvico\n" +
                    "• Visite di Coloproctologia\n" +
                    "• Visita di Endocrinochirurgia\n" +
                    "• Visita Senologica\n" +
                    "• Chirurgia Ambulatoriale\n" +
                    "• Servizio di Enterostomia\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Si effettua con impegnativa del Medico di Medicina Generale o dello Specialista presso Sportelli CUP (Centro Unico di Prenotazione).\n" +
                    "Accesso diretto all’Ambulatorio per visite chirurgiche con carattere di Urgenza.\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "La consegna avviene all'atto della prestazione presso l'ambulatorio\n" +
                    "\n" +
                    "Quando:\n" +
                    "A fine prestazione\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "immediatamente, contestualmente alla visita. Gli esami istologici entro 15 circa; i referti istologici vengono consegnati mediante appuntamento prefissato.");
            item4.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item4.setAvailable(true);
            post(item4);

            Item item5 = new Item();
            item5.setAuthor(ur.getById("admin"));
            item5.setCategory(cr.getById("Dietologia e Nutrizione Clinica"));
            item5.setType(ItemType.Offerta);
            item5.setTitle("Dietologia e Nutrizione Clinica");
            item5.setDescription("L'attività del Servizio si esplica a livello ospedaliero, ambulatoriale e territoriale ed è diretta a soggetti adulti affetti da patologie: non vengono pertanto effettuate attività rivolte a soggetti sani.\n" +
                    "\n" +
                    "A livello ospedaliero su richiesta dei medici dei reparti il servizio svolge attività di consulenza e presa in carico di pazienti ricoverati, affetti da malnutrizione e/o patologie sensibili alla dieta, per i quali vi sia necessità di uno specifico intervento nutrizionale: diete personalizzate, integrazione orale, nutrizione artificiale (enterale e/o parenterale); se è necessaria la continuazione a domicilio della terapia nutrizionale impostata durante la degenza viene effettuata una ulteriore visita alla dimissione con impostazione del programma di follow-up.\n" +
                    "\n" +
                    "Inoltre collabora con la ditta di ristorazione nella elaborazione del dietetico ospedaliero (raccolta di diete standardizzate per la popolazione degente in una struttura ospedaliera) e nella sua applicazione pur non avendo una responsabilità diretta sull’attività della cucina.\n" +
                    "\n" +
                    "A livello ambulatoriale la tipologia di pazienti è estremamente ampia, facendo sempre riferimento alle problematiche della malnutrizione per eccesso (sovrappeso, obesità e patologie correlate) e per difetto (quali malnutrizione oncologica e non oncologica, disfagia, disturbi del comportamento alimentare).\n" +
                    "\n" +
                    "Le modalità di prenotazione per le visite ambulatoriali variano a seconda della patologia.\n" +
                    "\n" +
                    "Sovrappeso/obesità. Attività rivolta ad adulti con sovrappeso/obesità (BMI ≥ 25). Si accede all'ambulatorio con impegnativa del Medico di Famiglia o di Specialista Ospedaliero indicante la motivazione della visita (sovrappeso, obesità, eventuali patologie associate) e il valore di BMI, tramite prenotazione mediante CUP. L'attività ambulatoriale si svolge il giovedì a Verbania e il venerdì a Domodossola a partire dalle ore 9.00; alla visita occorre presentare adeguata documentazione clinica ed esami ematochimici recenti.\n" +
                    "\n" +
                    "Malnutrizione e Nutrizione artificiale domiciliare (NAD). Attività dedicata sia a pazienti malnutriti o a rischio di malnutrizione affetti da malattie neurologiche degenerative, disfagia, esiti di chirurgia gastrointestinale, disturbi del comportamento alimentare, malattie renali (insufficienza renale cronica, emodialisi, dialisi peritoneale, trapianto) sia a pazienti deambulanti o trasportabili candidati alla NAD o già in trattamento (fase di monitoraggio). Si accede all'ambulatorio con impegnativa del Medico di Famiglia o di Specialista Ospedaliero, su appuntamento, dopo aver contattato direttamente la Struttura (di persona o telefonicamente o via mail). L'attività ambulatoriale (prime visite e controlli) si svolge il lunedì a Verbania, il martedì a Domodossola sempre a partire dalle ore 9.00 e prossimamente anche il mercoledì a Omegna.\n" +
                    "\n" +
                    "Gli appuntamenti per pazienti oncologici sono gestiti direttamente dal CAS con specifica agenda dedicata.\n" +
                    "\n" +
                    "Alla visita occorre presentare adeguata documentazione clinica ed eventuali esami ematochimici già effettuati.\n" +
                    "\n" +
                    "Tutte le visite di controllo vengono prenotate direttamente dalla struttura e fissate alla fine di ogni visita con tempi variabili dipendenti dalla situazione clinica.\n" +
                    "\n" +
                    "A livello territoriale è possibile l’attuazione di visite domiciliari per pazienti malnutriti o a rischio di malnutrizione  o in NAD non deambulanti o comunque non trasportabili residenti nel territorio dell’ ASL previa richiesta del medico curante. Si sottolinea che non si tratta di prestazioni urgenti e che vanno effettuate in caso di reale necessità per evitare abusi o richieste inadeguate e compatibilmente con la disponibilità del personale del servizio.\n" +
                    "\n" +
                    "All’attività assistenziale si aggiunge l’attività formativa sui problemi nutrizionali per il personale ospedaliero, per i medici di medicina generale e per il personale dell’ ASL e delle case di riposo; il personale del servizio può inoltre essere coinvolto insieme ad altri specialisti in eventi divulgativi e informativi sui problemi dell’alimentazione diretti alla popolazione.");
            item5.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item5.setAvailable(true);
            post(item5);

            Item item6 = new Item();
            item6.setAuthor(ur.getById("admin"));
            item6.setCategory(cr.getById("Endocrinologia e Diabetologia"));
            item6.setType(ItemType.Offerta);
            item6.setTitle("Endocrinologia e Diabetologia");
            item6.setDescription("Ambulatorio diabetologia\n" +
                    "Ambulatorio endocrinologia\n" +
                    "Amulatorio piede diabetico\n" +
                    "Ambulatorio diabete gestazionale\n" +
                    "Ambulatorio cardio-diabetologia\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Interni e CUP\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "Poliambulatori B piano terra\n" +
                    "\n" +
                    "Quando:\n" +
                    "al termine della visita");
            item6.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item6.setAvailable(true);
            post(item6);

            Item item7 = new Item();
            item7.setAuthor(ur.getById("admin"));
            item7.setCategory(cr.getById("Endoscopia dell’apparato digerente"));
            item7.setType(ItemType.Offerta);
            item7.setTitle("Endoscopia dell’apparato digerente");
            item7.setDescription("All'Ospedale Castelli: Esofagogastroduodonoscopia, Rettosigmoidoscopia, Colonscopie e attività opeativa endoscopica e breath test per ricerca Helicobacter Pilory, E.R.C.P.\n" +
                    "\n" +
                    "All'Ospedale San Biagio: Esofacogastroduodenoscopia, Rettosigmoidoscopia, Colonscopie e attività operativa endoscopica\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "tramite CUP o con accesso diretto per soggetti ricoverati o per prestazioni urgenti\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "Primo piano ala nuova (accanto DH Medicina)\n" +
                    "\n" +
                    "Quando:\n" +
                    "I referti di esame endoscopico vengono consegnati dopo l'esecuzione dell'esame stesso\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "I referti di esami istologici si ritirano presso l' ufficio Ritiro Referti (piano terra) da lunedì a venerdì dalle ore 8,30 alle 12,30 e dalle 13,30 alle 16,00");
            item7.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item7.setAvailable(true);
            post(item7);

            Item item8 = new Item();
            item8.setAuthor(ur.getById("admin"));
            item8.setCategory(cr.getById("Geriatria"));
            item8.setType(ItemType.Offerta);
            item8.setTitle("Geriatria");
            item8.setDescription("Visita geriatrica: indirizzata a quegli anziani con problematiche complesse e spesso affetti da più patologie, generalmente indicati con il termine di “fragili”\n" +
                    "Valutazione multidimensionale: a completamento della documentazione per l’invalidità civile\n" +
                    "Visita Neurogeriatrica (presso CDCD: Centro per i Disturbi Cognitivi e Demenze ) per pazienti con disturbi di memoria e per la diagnosi e la cura delle demenze e delle loro complicanze. Vengono fornite anche informazioni a chi si prende cura di questi malati\n" +
                    "Corsi di informazione per i familiari dei malati affetti da demenza: organizzati in più incontri, hanno lo scopo di fornire ai familiari informazioni sulle demenze, sui sintomi e sulle sue complicanze ma anche sulle strategie da adottare per una migliore gestione del paziente. Inoltre vengono fornite indicazioni sugli aspetti giuridici e medico-legali collegati alla demenza\n" +
                    "Call-line geriatrica per la demenza: linea telefonica per i pazienti seguiti presso l’ambulatorio CDCD e i loro familiari\n" +
                    "Visita per prescrizione ausili: per identificare e prescrivere gli eventuali necessari ausili al fine di contrastare la non autosufficienza e ridurne, per quanto possibile, i conseguenti disagi\n" +
                    "Visita domiciliare: tale prestazione è rivolta esclusivamente a pazienti allettati o gravemente non autosufficienti trasportabili presso gli ambulatori solo con ambulanza\n" +
                    "Unità Valutativa Geriatrica (U.V.G.): l’U.V.G. lavora in équipe (geriatra, assistente sociale, infermiere o assistente sanitaria) ed utilizzando le tecniche della valutazione multidimensionale e le specifiche competenze geriatriche fornisce indicazioni per i percorsi di cura dall’ospedale (attività ospedaliera) o dal domicilio (attività territoriale) per anziani non autosufficienti o a grave rischio di non autosufficienza\n" +
                    "Ambulatorio per l’osteoporosi: si occupa della diagnosi e cura dell’osteoporosi, malattia molto diffusa e ad elevato impatto sull’autonomia\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Per accedere all'ambulatorio è necessaria una impegnativa del Medico di Medicina Generale. L’accesso è su appuntamento: si prenota chiamando il CUP al numero 840.709210 (da telefono fisso e da cellulare).\n" +
                    "E' opportuno: \n" +
                    "presentarsi alla visita in abiti comodi, muniti di occhiali da vista e di eventuali protesi acustiche\n" +
                    "portare in visione la documentazione clinica precedente: eventuali accertamenti strumentali o esami ematici, referti di visite, cartelle di ricoveri ecc. ecc.. \n" +
                    "produrre l’elenco dei farmaci che il paziente sta assumendo\n" +
                    "segnalare eventuali intolleranza o allergie note a farmaci utilizzati in precedenza\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "\"Ospedale “Castelli” di Verbania presso di poliambulatorio B\n" +
                    "\n" +
                    "Quando:\n" +
                    "Per le sedi di Omegna e Verbania dal lunedì al venerdì, dalle ore 8.00 alle ore 16.00\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Al termine della visita");
            item8.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item8.setAvailable(true);
            post(item8);

            Item item9 = new Item();
            item9.setAuthor(ur.getById("admin"));
            item9.setCategory(cr.getById("Laboratorio Analisi"));
            item9.setType(ItemType.Offerta);
            item9.setTitle("Laboratorio Analisi");
            item9.setDescription("Prelievi, esami di liquidi biologici, chimica clinica, elettroforesi, ematologia, coagulazione, immunometria, virologia, microbiologia, biologia molecolare, esami urgenti \n" +
                    "\n" +
                    "I prelievi si effettuano dalle 7,30 alle 11,00 dal lunedì al venerdì, consentito in accesso diretto per:\n" +
                    "\n" +
                    "impegnative con priorità U (urgenti)\n" +
                    "impegnative con esenzione 048 (oncologici), 013 (diabetici), C01- C02 (invalidi al 100%)\n" +
                    "utenti TAO\n" +
                    "gravide\n" +
                    "utenti con deficit motiorio (in carrozzina, con ausili e relativo accompagnatore)\n" +
                    "utenti nefropatici inviati da ambulatorio ASL VCO\n" +
                    "utenti CSM inviati da ambulatorio ASL VCO\n" +
                    "Tutti gli altri utenti in possesso di imegnative con priorità B-D-P e quelli non compresi nelle categorie sopra elencate devono prenotare:\n" +
                    "\n" +
                    "al numero verde 800.000.500 - attivo tutti i giorni dalle ore 8,00 alle ore 20,00 (festivi esclusi)\n" +
                    "allo sportello CUP dell'Ospedale San Biagio di Domodossola - aperto da lunedì a venerdì dalle ore 8,30 alle ore 16,00\n" +
                    "allo sportello CUP dell'Ospedale Castelli di Verbania - aperto da lunedì a venerdì dalle ore 8,30 alle ore 12,35 e dalle ore 13,30 alle ore 16,00\n" +
                    "al front-office del Centro Ortopedico di Quadrante - aperto da lunedì a venerdì dalle ore 8,30 alle ore 16,00\n" +
                    " \n" +
                    "Si consiglia di precompilare il QUESTIONARIO DI VALUTAZIONE PRIMO LIVELLO predisposto dall’ASL per la valutazione obbligatoria di accesso al Presidio Ospedaliero per prestazioni sanitarie che dovrà essere consegnato in Laboratorio, scaricabile    \n" +
                    " o disponibile presso il Laboratorio il giorno del prelievo.\n" +
                    " \n" +
                    "\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "\n" +
                    "alla segreteria del Laboratorio Analisi di Domodossola - dalle ore 11,00 alle ore 13,00 e dalle ore 14,00 alle ore 15,30\n" +
                    "alla segreteria del Laboratorio Analisi di di Verbania - dalle ore 12,00 alle ore 15,00\n" +
                    "al front-office del Centro Ortopedico di  Quadrante di Omegna - dalle ore 8,30 alle ore 16,00\n" +
                    "online o tramite invio a domicilio facendone richiesta allo sportello dell'accettazione\n" +
                    "Al momento del ritiro agli sportelli, va presentato l'apposito foglio del pagamento ticket effettuato, ove previsto\n" +
                    "\n" +
                    "Nessun operatore è autorizzato a rilasciare referti verbali o per via telefonica a utenti ambulatoriali.\n" +
                    "\n" +
                    "In caso di necessità, l'unico dialogo possibile è tra il personale dirigente del laboratorio e il personale medico esterno.\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "dalla data indicata sul foglio di accettazione e non oltre un mese da tale data\n" +
                    "\n" +
                    "Note:\n" +
                    "Al momento dell'accettazione e/o della prenotazione viene consegnato un modulo per il pagamento del ticket pagabile online tramite il portale regionale  \n" +
                    " ai punti gialli,  allo sportello bancario interno all'ospedale, allo sportello CUP dell'Ospedale San Biagio esclusivamente tramite carta di pagamento");
            item9.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item9.setAvailable(true);
            post(item9);

            Item item10 = new Item();
            item10.setAuthor(ur.getById("admin"));
            item10.setCategory(cr.getById("Malattie Infettive e Tropicali"));
            item10.setType(ItemType.Offerta);
            item10.setTitle("Malattie Infettive e Tropicali");
            item10.setDescription("Ambulatorio infettivologico\n" +
                    "Diagnosi cura terapia e counseling delle infezioni da HIV, HCV, HBV, MST\n" +
                    "Indicazioni terapeutiche per il viaggiatore internazionale\n" +
                    "Consulenze per il DEA\n" +
                    "Consulenze nella gestione delle infezioni ospedaliere\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "accesso tramite CUP tranne per l'ambulatorio Malattie Sessualmente Trasmissibili\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "ambulatorio\n" +
                    "\n" +
                    "Quando:\n" +
                    "da lunedì al venerdì\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "in base agli esami eseguiti");
            item10.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item10.setAvailable(true);
            post(item10);

            Item item11 = new Item();
            item11.setAuthor(ur.getById("admin"));
            item11.setCategory(cr.getById("Medicina Interna"));
            item11.setType(ItemType.Offerta);
            item11.setTitle("Medicina Interna");
            item11.setDescription("Servizi: Allergologia, Pneumologia e Dietologia\n" +
                    "Ambulatori: Ecografia internistica, Celiachia, Gastroenterologia, Allergologia, Ematologia e Oncoematologia, Epatologia, Medicina Interna, Diagnostica vascolare, Angiologia, Ipertensione arteriosa, Monitoraggio ambulatoriale 24,00 ore della pressione arteriosa, Scompenso cardiaco cronico, Centro Emostasi e trombosi\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Centro Unico Prenotazione\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Day Service\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "Day Hospital e Ambulatori Medicina Interna\n" +
                    "\n" +
                    "Quando:\n" +
                    "immediatamente");
            item11.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item11.setAvailable(true);
            post(item11);

            Item item12 = new Item();
            item12.setAuthor(ur.getById("admin"));
            item12.setCategory(cr.getById("Nefrologia e Dialisi"));
            item12.setType(ItemType.Offerta);
            item12.setTitle("Nefrologia e Dialisi");
            item12.setDescription("Degenza Nefrologica:\n" +
                    "\n" +
                    "Nefrologia Clinica: inquadramento diagnostico terapeutico delle nefropatie primitive e secondarie e del successivo follow-up clinico e strumentale\n" +
                    "Diagnostica istologica nefropatie tramite agobiopsia renale ecoguidata\n" +
                    "Gestione clinica delle complicanze della malattia renale cronica ed alla e delle comorbidità ad essa correlate\n" +
                    "Gestione clinica dei quadri di insufficienza renale acuta necessitanti o meno di terapia sostitutiva della funzione renale\n" +
                    "Attività Chirurgica accessi per dialisi (posizionamento cateteri venosi centrali temporanei ed a permanenza, creazione fistole artero venose con vasi nativi o protesici, posizionamento di cateteri peritoneali)\n" +
                    "Consulenze nefrologiche presso reparti e servizi ospedalieri\n" +
                    "Servizio di pronta disponibilità\n" +
                    "\n" +
                    "Prenotazione Ricovero:\n" +
                    "Presso il reparto di Nefrologia, dopo valutazione specialistica, segnalazione dei dati anagrafici e diagnosi clinica in apposito registro, comunicazione immediata della presunta data di ricovero");
            item12.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item12.setAvailable(true);
            post(item12);

            Item item13 = new Item();
            item13.setAuthor(ur.getById("admin"));
            item13.setCategory(cr.getById("Neurologia"));
            item13.setType(ItemType.Offerta);
            item13.setTitle("Neurologia");
            item13.setDescription("Ambulatorio di Neurologia Generale\n" +
                    "Ambulatorio CDCD\n" +
                    "Ambulatorio Sclerosi Multipla\n" +
                    "Ambulatorio di Neurochirurgia in convenzione con Neurochirurgia di Novara (per pazienti ricoverati o precedentemente operati e previo accordo con il personale ambulatoriale)\n" +
                    "Elettroencefalografia\n" +
                    "Elettromiografia\n" +
                    "Potenziali Evocati\n" +
                    "Ecodoppler Tronchi Sopra-Aortici\n" +
                    "Ecodoppler transcranico\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Visite Neurologia Generale: lunedì mattina e pomeriggio - martedì mattina - mercoledì mattina - giovedì mattina e pomeriggio - venerdì mattina\n" +
                    "Ecodoppler Tronchi Sovraortici: martedì pomeriggio\n" +
                    "Ecodoppler Transcranici: venerdì mattina\n" +
                    "Potenziali evocati: lunedì pomeriggio - venerdì mattina e pomeriggio\n" +
                    "Elettromiografia: lunedì, martedì e mercoledì mattina e pomeriggio\n" +
                    "Elettroencefalografia: dal lunedì al venerdì mattina e pomeriggio - sabato mattina\n" +
                    "Ambulatorio CDCD: prima visita prenotazione tramite CUP - visite: giovedì pomeriggio a Domodossola - martedì pomeriggio a Verbania\n" +
                    "Ambulatorio Sclerosi Multipla: su prenotazione diretta in ambulatorio (0324.491201)\n" +
                    "Ambulatorio Neurochirurgia: ogni due settimane su prenotazione diretta in ambulatorio (0324.491201)\n" +
                    "\n" +
                    "Attività strumentali:\n" +
                    "Elettroencefalografia, Elettromiografia, Potenziali Evocati, Ecodoppler Tronchi Sopra-Aortici, Ecodoppler transcranico: dal lunedì al venerdì\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "La consegna del referto avviene presso l'Ambulatorio dove è stato eseguito l'esame o effettuata la visita\n" +
                    "\n" +
                    "Quando:\n" +
                    "a fine prestazione per: visite, elettromiografie, ecodoppler tronchi sopra-aortici, ecodoppler transcranico\n" +
                    "entro due giorni lavorativi per: elettroencefalogrammi e potenziali evocati\n" +
                    "su richiesta dell’utente è possibile inviare il referto via posta a domicilio con raccomandata (gratuitamente se utente esente ticket per l’esame)\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "1-Immediata 2-Entro due giorni lavorativi 3-Tramite Raccomandata\n" +
                    "\n" +
                    "Note:\n" +
                    "E’ prevista l’effettuazione di:\n" +
                    "- Visite Neurologiche Urgenti in Classe A (effettuate con Impegnativa del Medico Curante o dello Specialista entro 72 ore dalla richiesta) presso l’Ambulatorio di Domodossola dal lunedì al venerdì dalle 8.00 alle 20.00 e sabato dalle 9.00 alle 12.00 e presso l’Ambulatorio di Verbania e di Omegna nei giorni e negli orari di presenza del neurologo\n" +
                    "\n" +
                    "- Visite Neurologiche in Classe B (effettuate con impegnativa del Medico Curante o dello Specialista entro 10 giorni dalla richiesta) presso l’Ambulatorio di Domodossola dal lunedì al venerdì dalle 8.00 alle 20.00 e sabato dalle 9.00 alle 12.00 e presso l’Ambulatorio di Verbania e di Omegna nei giorni e negli orari di presenza del neurologo, su prenotazione CUP o direttamente previo contatto con Ambulatorio\n" +
                    "\n" +
                    "- Elettroencefalogrammi urgenti a Domodossola dal lunedì al venerdì mattina e pomeriggio e sabato mattina con impegnativa del Medico Curante e a Verbania dal lunedì al venerdì mattina\n" +
                    "\n" +
                    "- Elettromiografie urgenti a Domodossola solo con impegnativa urgente redatta dallo specialista Neurologo, Ortopedico, Fisiatra\n" +
                    "\n" +
                    "- Elettromiografie in Classe B a Domodossola previo accordo diretto con Ambulatorio\n" +
                    "\n" +
                    "- Ecodoppler Tronchi Sopra-Aortici urgenti a Domodossola previo accordo diretto con Ambulatorio");
            item13.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item13.setAvailable(true);
            post(item13);

            Item item14 = new Item();
            item14.setAuthor(ur.getById("admin"));
            item14.setCategory(cr.getById("Neuropsichiatria Infantile"));
            item14.setType(ItemType.Offerta);
            item14.setTitle("Neuropsichiatria Infantile");
            item14.setDescription("1) Attività ambulatoriale:\n" +
                    "-visita neurologica\n" +
                    "-colloquio psicologico clinico\n" +
                    "-colloqui con i genitori in ambito di di valutazione, sostegno e counseling\n" +
                    "-valutazione psicodiagnostica\n" +
                    "-valutazione delle funzioni neuropsicologiche\n" +
                    "-valutazione e trattamento dei disturbi neuropsicomotori\n" +
                    "-interventi di sostegno educativo\n" +
                    "-rieducazione dei disturbi cognitivi e comportamentali\n" +
                    "-valutazione e presa in carico riabilitativa dei disturbi del linguaggio e dell’apprendimento\n" +
                    "\n" +
                    "2) Ambulatorio follow-up del neonato pretermine e/o con basso peso alla nascita e/o con patologia dopo la dimissione dal Punto Nascita o dalla Terapia Intensiva Neonatale in collaborazione con la Pediatria.\n" +
                    "3) Attività di consulenza per i reparti di Pediatria e di SPDC e collaborazione nella gestione del DH psichiatrico per adolescenti in fase post-acuta.\n" +
                    "4) Valutazione e presa in carico  di soggetti autistici e delle loro famiglie (progetto con fondi regionali  D.G.R. n.2-4286 del 29 Novembre 2016: DGR n.26-1653 del 29.6.2015)\n" +
                    "5) Attività di collaborazione con i Distretti Sanitari per i progetti di prevenzione primaria (area materno infantile).\n" +
                    "6) Certificazione per gli alunni disabili ai sensi della L.104/92 e certificazioni DSA ai sensi della L. 170/2010 e successivo DGR 16/2014.\n" +
                    "7) Interventi di tutela e di sostegno per bambini maltrattati ed abusati attraverso l’equipe Maltrattamento e Abuso in collaborazione con i Consorzi dei Servizi Sociali e i servizi specialistici dell’ASL. \n" +
                    "8) Attività di valutazione e presa in carico quando disposta dall'Autorità Giudiziaria Ordinaria e Minorile. \n" +
                    "9)  Attività relativa agli affidi e alle adozioni.\n" +
                    "10) Collaborazione con il Consultorio al percorso sulla depressione post-partum\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "PRENOTAZIONE AMBULATORIO\n" +
                    "\n" +
                    "L'accesso alla SOC NPI è diretto, non è indispensabile l'impegnativa del medico, le prestazioni erogate sono completamente gratuite \n" +
                    "L'appuntamento può essere richiesto dai genitori (o, dove previsto, da un tutore) recandosi personalmente presso le sedi territoriali della SOC NPI aperte al pubblico dal lunedì al venerdì dalle ore 9,00 alle 16,00 oppure telefonando negli stessi orari");
            item14.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item14.setAvailable(true);
            post(item14);

            Item item15 = new Item();
            item15.setAuthor(ur.getById("admin"));
            item15.setCategory(cr.getById("Oculistica"));
            item15.setType(ItemType.Offerta);
            item15.setTitle("Oculistica");
            item15.setDescription("Consulenze oculistiche (urgenti – consulenze a pazienti ricoverati – postchirurgiche)\n" +
                    "Esami ortottici, esame del campo visivo computerizzato, pachimetria corneale\n" +
                    "Ecografia, ecobiometria a contatto e a ultrasuoni\n" +
                    "Elettrofisiologia oculare\n" +
                    "Angiografia retinica con fluoresceina e verde indocianina, esame OCT (Tomografia a Coerenza Ottica), retinografia\n" +
                    "Imaging HRT per il glaucoma\n" +
                    "Laser terapia, retinica, per il glaucoma e per la cataratta secondaria (Argon e ND:Yag)\n" +
                    "Segmento posteriore: distacco di retina\n" +
                    "Segmento anteriore: cataratta, glaucoma, annessi, iniezioni intravitrali di anti-VEGF per la cura delle maculopatie\n" +
                    "Chirurgia in Day-Surgery e in ricovero ordinario\n" +
                    "Chirurgia Ambulatoriale: plastica palpebrale, delle vie lacrimali e degli annessi\n" +
                    "Day-Service: diagnosi e cura senza ricovero di patologie oculari acute e croniche\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Si effettua presso gli sportelli CUP o mediante servizio CUP telefonico regionale al numero 800.000.500 tutti i giorni dalle ore 8,00 alle ore 20,00, festività nazionali escluse con impegnativa del Medico di Famiglia o dello Specialista per: esami del campo visivo, piccoli interventi sugli annessi, fluorangiografia retinica, visite post-ricovero e di controllo, esami ortottici, pachimetria corneale, esame HRT, esami del campo visivo e del senso cromatico.\n" +
                    "Per laser terapia ed elettrofisiologia oculare la prenotazione si effettua presso gli ambulatori laser di Domodossola anche per telefono (0324 491448/491231) dal lunedì al venerdì dalle 08.00 alle 15.30\n" +
                    "\n" +
                    "Attività ambulatoriale:\n" +
                    "Visite urgenti richieste dal Pronto Soccorso o dal Medico di Famiglia, visite post-ricovero chirurgiche e mediche\n" +
                    "Angiografia retinica con fluoresceina e verde indocianina\n" +
                    "Esame OCT\n" +
                    "Imaging papillare HRT per il glaucoma\n" +
                    "Ecografia oculare A - B scan\n" +
                    "Laserterapia (Argon e ND:Yag)\n" +
                    "Esami ortottici\n" +
                    "Esame del campo visivo e pachimetria corneale\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "- Week Surgery\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Tempi di Consegna:\n" +
                    "La consegna dei referti avviene alla fine della prestazione presso l'ambulatorio\n" +
                    " \n" +
                    "\n" +
                    "Note:\n" +
                    "Ogni volta che si effettua una prestazione è necessario presentarsi con tutta la documentazione relativa alle pregresse condizioni oculari anche nel caso delle visite urgenti (richieste dal Pronto Soccorso o dal Medico di Famiglia)");
            item15.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item15.setAvailable(true);
            post(item15);

            Item item16 = new Item();
            item16.setAuthor(ur.getById("admin"));
            item16.setCategory(cr.getById("Oncologia"));
            item16.setType(ItemType.Offerta);
            item16.setTitle("Oncologia");
            item16.setDescription("Prestazioni:\n" +
                    "• attività di ricovero ordinario\n" +
                    "• attività di Day Hospital\n" +
                    "• attività ambulatoriali\n" +
                    "\n" +
                    "Attività medica:\n" +
                    "• Visite mediche specialistiche oncologiche: prime visite, visite di controllo e visite di follow-up\n" +
                    "• Biopsie diagnostiche\n" +
                    "• Discussioni collegiali sui casi clinici per condividere le scelte diagnostiche e terapeutiche\n" +
                    "• Discussioni multidisciplinari tra Specialisti\n" +
                    "• Colloqui con Parenti degli assistiti\n" +
                    "• Terapie:\n" +
                    "Chemioterapie\n" +
                    "Immunoterapie\n" +
                    "Terapie Biologiche\n" +
                    "Terapie Ormonali\n" +
                    "Emotrasfusioni\n" +
                    "Terapie di supporto\n" +
                    "Controllo del dolore\n" +
                    "• Toracentesi, paracentesi, talcaggio pleurico\n" +
                    "\n" +
                    "Attività infermieristica:\n" +
                    "Somministrazione terapie prescritte\n" +
                    "Posizionamento cateteri venosi centrali ad inserimento periferico ( PICC e Midline)\n" +
                    "Gestione cateteri venosi centrali\n" +
                    "Medicazioni\n" +
                    "Educazione sanitaria e terapeutica ad utenti e familiari\n" +
                    "Supporto psicologico a pazienti oncologici e familiari\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Presso CAS (Centro Accoglienza e Servizi), Area A - primo piano Ospedale Castelli VB- tel 0323.541539.\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "presso CAS, Area A - primo  piano Ospedale di Verbania - tel 0323. 541539\n" +
                    "\n" +
                    "Quando:\n" +
                    "Referti di visita a fine prestazione\n" +
                    "Referti esami strumentali: da lunedì a venerdì dalle 9,00 alle 12,30\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Referti di visita a fine prestazione\n" +
                    "Referti esami strumentali dal Lunedì al Venerdì dalle ore 9.00 alle ore 12.30 presso CAS\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "CAS - Centro Accoglienza Servizi\n" +
                    "\n" +
                    "Il CAS è un servizio messo a disposizione dalla Rete Oncologica del Piemonte e della Valle D’Aosta per tutte le persone con sospetta o accertata patologia oncologica.\n" +
                    "\n" +
                    "Il CAS, situato di norma presso i Servizi Oncologici delle Aziende Sanitarie ad esso afferenti:\n" +
                    "\n" +
                    "Accoglie il paziente, informandolo in merito ai servizi erogati, alle modalità di accesso, alle prenotazioni;\n" +
                    "\n" +
                    "Svolge mansioni sul versante amministrativo-gestionale lungo tutto il percorso diagnostico-terapeutico del paziente, prevedendo le seguenti principali attività:\n" +
                    "\n" +
                    "- rilascio esenzione 048\n" +
                    "\n" +
                    "- prenotazione delle prestazioni diagnostiche preliminari\n" +
                    "\n" +
                    "- attivazione del Gruppo Interdisciplinare Cure di riferimento\n" +
                    "\n" +
                    "- prenotazione prime visite oncologiche e successive visite di controllo\n" +
                    "\n" +
                    "Fornisce informazioni su modalità di accesso ai Servizi, prenotazioni, professionalità disponibili, centri specializzati, orari\n" +
                    "\n" +
                    "Informa la persona e i familiari sui “diritti” spettanti in ambito sociale relativi alla sua patologia\n" +
                    "\n" +
                    "Gestisce i rapporti con i Centri Accoglienza e Servizio delle altre sedi regionali\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Orari:\n" +
                    "Da Lunedì a Venerdì - dalle ore 8.00 alle ore 12.30");
            item16.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item16.setAvailable(true);
            post(item16);

            Item item17 = new Item();
            item17.setAuthor(ur.getById("admin"));
            item17.setCategory(cr.getById("Ortopedia Traumatologia"));
            item17.setType(ItemType.Offerta);
            item17.setTitle("Ortopedia Traumatologia");
            item17.setDescription("REPARTO DEGENZA e DAY SURGERY:\n" +
                    "letti ricovero in regime ordinario ed in regime di Day Surgery\n" +
                    "Camere a 2 o 4 letti con servizio igienico interno\n" +
                    "\n" +
                    "ATTIVITA' AMBULATORIALE OSPEDALIERA\n" +
                    "Visite specialistiche ortopediche\n" +
                    "Visite controllo e medicazioni post ricovero; DEA; ambulatoriali\n" +
                    "Ambulatorio Protesi anca e ginocchio - spalla\n" +
                    "Prescrizione ausili ortopedici\n" +
                    "SALA GESSI DEA - CONSULENZE ORTOPEDICHE DEA - VISITE URGENTI:\n" +
                    "Accesso previa visita DEA tutti i giorni dalle ore 13,30 alle 15,30.\n" +
                    "Dopo le 16,00 e nei festivi solo in regime di reperibilità\n" +
                    "\n" +
                    "ATTIVITA' SPECIALISTICA TERRITORIALE\n" +
                    "Presso le sedi del distretto sparse sul territorio\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "prenotazione presso il CUP con impegnativa del curante o con impegnativa per prestazioni interne o prenotazione diretta interna\n" +
                    "\n" +
                    "Visite specialistiche ortopediche e visite di controllo programmabili\n" +
                    "Lunedì - Giovedì ore 13,30 - 15,30 ( Prenotazione al CUP)\n" +
                    "Visite di controllo DEA, post ricovero e ambulatorio\n" +
                    "Lunedì - Mercoledì - Giovedì - Venerdì ore 9,00 - 12,00 (Prenotazione interna)\n" +
                    "\n" +
                    "Infiltrazioni, medicazioni, rimozione e confezione gessi:\n" +
                    "Lunedì - Mercoledì - Giovedì - Venerdì ore 8,00 - 9,00 (Prenotazione al CUP e interna)\n" +
                    "\n" +
                    "SALA GESSI DEA e Visite urgenti:\n" +
                    "Accesso previa visita DEA tutti i giorni dalle ore 13,30 alle 15,30\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "- Week Surgery\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "La consegna avviene all'atto della prestazione presso l'ambulatorio\n" +
                    "\n" +
                    "Quando:\n" +
                    "A fine prestazione\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Tempo reale");
            item17.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item17.setAvailable(true);
            post(item17);

            Item item18 = new Item();
            item18.setAuthor(ur.getById("admin"));
            item18.setCategory(cr.getById("Ostetricia Ginecologia"));
            item18.setType(ItemType.Offerta);
            item18.setTitle("Ostetricia Ginecologia");
            item18.setDescription("Ginecologia ed ostetricia generali. Oncologia ginecologica, Uroginecologia, Diagnosi prenatale\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Presso il CUP (sportelli e telefonico)\n" +
                    "Diagnosi prenatale al numero 0323.541348.\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "- Week Surgery\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "in ambulatorio\n" +
                    "\n" +
                    "Quando:\n" +
                    "Immediati nella diagnostica per immagini.\n" +
                    "Citologici ed istologici presso Anatomia Patologica\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "In relazione ai tempi di laboratorio");
            item18.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item18.setAvailable(true);
            post(item18);

            Item item19 = new Item();
            item19.setAuthor(ur.getById("admin"));
            item19.setCategory(cr.getById("Otorinolaringoiatria"));
            item19.setType(ItemType.Offerta);
            item19.setTitle("Otorinolaringoiatria");
            item19.setDescription("Attività ambulatoriale:\n" +
                    "visite specialistiche otorinolaringoiatriche e maxillo facciali, medicazioni, visite post ricovero, esame audiometrico, esame impedenzometrico,potenziali evocati uditivi, esame vestibolare con studio della funzione labirintica, ambulatorio dermochirurgico della testa e del collo, endoscopia nasosinusale e laringea, otomicroscopia con microscopio diagnostico.\n" +
                    "Attività chirurgica:\n" +
                    "interventi di adenoidectomia e tonsillectomia; chirurgia del naso e dei seni paranasali ( videoendoscopia nasosinusale funzionale e oncologica; rino-settoplastica funzionale); chirurgia delle ghiandole salivari maggiori (parotide, sottomandibolari, sottolinguale); chirurgia della tiroide e delle paratiroidi; chirurgia oncologica della testa e del collo con chirurgia plastica ricostruttiva (massiccio facciale, cavo orale, faringe, laringe, tiroide e cute del volto); chirurgia della patologia disembriogenetica (cisti branchiali, cisti del dotto tiro-glosso, cisti dermoidi della linea mediana, fistola auris); Chirurgia laser endoscopica laringea (laringectomie parziali endoscopiche); chirurgia dell'orecchio e della sordità (miringo-ossiculo-timpanoplastica, chirurgia della otosclerosi, esame audiometrico tonale e vocale, plastica dilatativa del condotto uditivo esterno) labirintectoma con gentamicina; chirurgia odontostomatologica e maxillo facciale (chirurgia dei denti inclusi, bonifica dentale in pazienti disabili o con gravi patologie sistemiche, dismorfosi maxillo mandibolari, incrementi volumetrici di osso in preparazione alla chirurgia implantare); chirurgia traumatologica delle ossa del massiccio facciale; chirurgia della patologa ostruttiva del sonno\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Effettuata con impegnativa del Medico di Famiglia o di un medico dell'ASL presso gli sportelli CUP (Centro Unico Prenotazione)\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "- Week Surgery\n" +
                    "- One -day surgery\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "La consegna avviene all'atto della prestazione presso l'ambulatorio\n" +
                    "\n" +
                    "Quando:\n" +
                    "A fine prestazione\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Immediatamente");
            item19.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item19.setAvailable(true);
            post(item19);

            Item item20 = new Item();
            item20.setAuthor(ur.getById("admin"));
            item20.setCategory(cr.getById("Pediatria"));
            item20.setType(ItemType.Offerta);
            item20.setTitle("Pediatria");
            item20.setDescription("Degenza\n" +
                    "Osservazione Breve intensiva\n" +
                    "Ambulatorio Infermieristico post dimissione neonatale\n" +
                    "Ambulatorio di pediatria allergologica\n" +
                    "Ambulatorio di genetica clinica\n" +
                    "Ambulatorio di ematologia\n" +
                    "Ambulatorio di malattie infettive a trasmissione verticale\n" +
                    "Ambulatorio per il bambino adottato immigrato\n" +
                    "Ambulatorio celiachia e gastroenterologia pediatrica\n" +
                    "Ambulatorio di Follow up Prematuri\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Attività ambulatoriale da prenotarsi Centro Unico di Prenotazione (CUP)\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- ricovero di pazienti chirurgici pediatrici in Area Pediatrica, in collaborazione con la Chirurgia, la Ortopedia.\n" +
                    "\n" +
                    "REFERTI\n" +
                    "\n" +
                    "Altro:\n" +
                    "Collaborazione con ABIO, associazione Bambini In Ospedale, che integra anche il progetto Nati per Leggere.\n" +
                    "Collaborazione con i Distretti territoriali, la NPI, il Dipartimento di Prevenzione per un progetto di educazione alla salute per i genitori di bimbi che frequentano gli asili e le scuole materne del VCO.\n" +
                    "Integrazione operativa con la NPI ed i Servizi Sociali nell'Equipe Abuso e maltrattamento dell'ASL VCO.\n" +
                    "Attività di ricerca clinica soprattutto nella prevenzione del dolore, della epidemiologia e prevenzione delle dipendenze (alcool, disturbi alimentazione) e genetica/dismorfologia.\n" +
                    "Ottenuto nel 2010 e confermato nel 2014 e nel 2018 l'accreditamento OMS/UNICEF Ospedale Amico dei Bambini, sulla promozione dell'allattamento al seno.");
            item20.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item20.setAvailable(true);
            post(item20);

            Item item21 = new Item();
            item21.setAuthor(ur.getById("admin"));
            item21.setCategory(cr.getById("Radiologia"));
            item21.setType(ItemType.Offerta);
            item21.setTitle("Radiologia");
            item21.setDescription("Esami radiologici del torace\n" +
                    "Esami radiologici dell' apparato scheletrico\n" +
                    "Esami contrastografici dell’apparato digerente e delle vie urinarie\n" +
                    "Mammografia\n" +
                    "TC multistrato\n" +
                    "Ecografia\n" +
                    "Risonanza magnetica articolare\n" +
                    "Terapia con onde d'urto focalizzate (ortopedica)\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Effettuata con impegnativa del Medico curante:\n" +
                    "presso gli sportelli della Radiologia dalle ore 8,00 alle ore 12,00 e dalle ore 13,00 alle ore 16,00\n" +
                    "dagli sportelli CUP (Centro Unico di Prenotazione ) dal lunedì al venerdì dalle 08,30 alle 12,30 e dalle 14,00 alle 16,00\n" +
                    "dal Cup telefonico (n. 840.709210 da fisso e da cellulare )\n" +
                    "Presso le Farmacie Convenzionate possono essere prenotati solo gli esami ossei ed ecografici\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "presso gli sportelli dell'ufficio referti\n" +
                    "\n" +
                    "Quando:\n" +
                    "lunedì al venerdì dalle ore 13,30 alle ore 16,00\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Entro 3 giorni dalla data di esecuzione, escluso Mammografia e TC (10 giorni) e comunque secondo le indicazioni impartite dal personale al momento del congedo del paziente\n" +
                    "\n" +
                    "Note:\n" +
                    "Si prega di leggere attentamente le istruzioni allegate alla preparazione, al fine di non invalidare l’esito dell’esame.\n" +
                    "Si ricorda che il ticket deve essere pagato prima dell'esecuzione dell'esame");
            item21.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item21.setAvailable(true);
            post(item21);

            Item item22 = new Item();
            item22.setAuthor(ur.getById("admin"));
            item22.setCategory(cr.getById("Radioterapia"));
            item22.setType(ItemType.Offerta);
            item22.setTitle("Radioterapia");
            item22.setDescription("Visite specialistiche per valutare l’indicazione alla radioterapia\n" +
                    "Visite di controllo in corso di trattamento\n" +
                    "Visite di follow-up oncologico\n" +
                    "Simulazione virtuale per la pianificazione dei trattamenti radianti e verifiche del trattamento\n" +
                    "Piani di trattamento con tecnica 3D-conformazionale, ad intensità modulata (IMRT), volumetrica con archi (VMAT), stereotassica (cranica ed extracranica).\n" +
                    "La struttura è dotata di:\n" +
                    "\n" +
                    "sistemi per piani di cura che consentono lo studio dei distretti anatomici di interesse e dei volumi di trattamento con possibilità di integrare metodiche diagnostiche multimodali quali TC, RM e TC-PET\n" +
                    "sistemi di verifica di trattamento quali Megavoltage Cone-beam CT e VisionRT che consentono l’esecuzione di una radioterapia guidata dalle immagini (IGRT) e una radioterapia adattativa (Adaptive RT)\n" +
                    "acceleratori lineari Siemens “Primus Plus” e Siemens “Artiste”.\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Prenotazione PRIMA VISITA:\n" +
                    "\n" +
                    "telefonica c/o C.A.S (Centro Accoglienza e Servizi): 0323-541539 dal lunedì al venerdì ore 8.30 – 15.30\n" +
                    "telefonica c/o CUP regionale al numero 800.000.500 tutti i giorni dalle ore 8,00 alle ore 20,00, festività nazionali escluse\n" +
                    "\n" +
                    "Prenotazione VISITA DI CONTROLLO:\n" +
                    "\n" +
                    "telefonica c/o C.A.S (Centro Accoglienza e Servizi): 0323-541539 dal lunedì al venerdì ore 8.30 – 15.30\n" +
                    "telefonica c/o CUP regionale al numero 800.000.500 tutti i giorni dalle ore 8,00 alle ore 20,00, festività nazionali escluse\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "La consegna avviene all'atto della visita presso l'ambulatorio.\n" +
                    "\n" +
                    "Quando:\n" +
                    "Al termine della visita\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "Immediatamente\n" +
                    "\n" +
                    "Altro:\n" +
                    "Collaborazione interdisciplinare con altri Specialisti (Chirurghi, Oncologi medici, Specialisti d'organo) presso i GIC (Gruppi Interdisciplinari Cure); gestione dipartimentale con l'Oncologia per i trattamenti integrati chemioradioterapici.");
            item22.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item22.setAvailable(true);
            post(item22);

            Item item23 = new Item();
            item23.setAuthor(ur.getById("admin"));
            item23.setCategory(cr.getById("Recupero e Rieducazione Funzionale"));
            item23.setType(ItemType.Offerta);
            item23.setTitle("Recupero e Rieducazione Funzionale");
            item23.setDescription("Visita specialistica fisiatrica per predisposizione di progetto riabilitativo individuale\n" +
                    "Valutazione ed effettuazione di programmi riabilitativi di fisioterapia e/o logoterapia per utenti con disabilità neurologiche, ortopediche-traumatologiche, reumatologiche, respiratorie, della comunicazione\n" +
                    "Prescrizione ausili e protesi\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Visita fisiatrica da prenotare al CUP\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Attività ambulatoriale\n" +
                    "- Attività domiciliare\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "In ambulatorio\n" +
                    "\n" +
                    "Quando:\n" +
                    "Al termine della visita e dell'eventuale ciclo di trattamento fisioterapico e/o logoterapico\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "A fine prestazione");
            item23.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item23.setAvailable(true);
            post(item23);

            Item item24 = new Item();
            item24.setAuthor(ur.getById("admin"));
            item24.setCategory(cr.getById("Servizio Immunoematologia e Trasfusionale"));
            item24.setType(ItemType.Offerta);
            item24.setTitle("Servizio Immunoematologia e Trasfusionale");
            item24.setDescription("Raccolta di sangue, plasma e piastrine da donatori di sangue volontari.\n" +
                    "Lavorazione delle unità di sangue raccolte con produzione di emocomponenti.\n" +
                    "Gestione urgenze ed emergenze, assegnazione distribuzione degli emocomponenti, consulenza di medicina trasfusionale e autotrasfusionale, raccolta unità autologhe, gestione trasfusioni ambulatoriali, supporto trasfusionale domiciliare, collaborazione con il Centro Regionale di Coordinamento e compensazione e con gli altri SIMT della macroarea Nord orientale del Piemonte .\n" +
                    "Visite periodiche ai donatori di sangue.\n" +
                    "Arruolamento nuovi iscrittti e gestione delle attività del registro dei donatori midollo osseo (IBMDR). Propaganda della cultura della donazione di sangue e midollo nelle scuole superiori di competenza.\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Per informazioni e prenotazioni iscrizione AVIS telefonare allo 0324.491272 dal lunedì al venerdì dalle 9,30-16,30;\n" +
                    "per informazioni e prenotazioni per iscrizioni per la donazioni di midollo contattare lo 0324.491388 dalle 10,30-12,30 oppure dalle 14,00-16,00 dal lunedì al venerdì.\n" +
                    "In alternativa si possono consultare i seguenti siti : www.domomidolloosseo.it\n" +
                    "www.avisdomo.it da cui ci si può iscrivere direttamente online\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Dove:\n" +
                    "Laboratorio Analisi\n" +
                    "\n" +
                    "Quando:\n" +
                    "Secondo gli orari predisposti dalla SOC Laboratorio Analisi\n" +
                    "\n" +
                    "Tempi di Consegna:\n" +
                    "2 gg per referti immunoematologia");
            item24.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item24.setAvailable(true);
            post(item24);

            Item item25 = new Item();
            item25.setAuthor(ur.getById("admin"));
            item25.setCategory(cr.getById("Servizio Psichiatrico di Diagnosi e Cura SPDC"));
            item25.setType(ItemType.Offerta);
            item25.setTitle("Servizio Psichiatrico di Diagnosi e Cura SPDC");
            item25.setDescription("L’attività di ricovero prevede due tipi di degenza ospedaliera: ordinaria (sulle 24 ore) o diurna (quest’ultima espletata all’interno del Day Hospital psichiatrico).\n" +
                    "\n" +
                    "Nel caso specifico di ricovero di utenti di minore età è prevista una stretta collaborazione con la SOC di Neuropsichiatria Infantile ed il coinvolgimento delle famiglie/tutori. Si tende a ridurre la durata dei ricoveri dei minori in SPDC ed a personalizzare i loro trattamenti, anche attraverso l’integrazione (in corso di degenza) di interventi di tipo riabilitativo-risocializzante in capo a personale specializzato convenzionato. Alle dimissioni, è prevista una temporanea presa in carico congiunta SPDC-NPI in fase post acuta,  all’interno del Day Hospital psichiatrico.");
            item25.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item25.setAvailable(true);
            post(item25);

            Item item26 = new Item();
            item26.setAuthor(ur.getById("admin"));
            item26.setCategory(cr.getById("Urologia"));
            item26.setType(ItemType.Offerta);
            item26.setTitle("Urologia");
            item26.setDescription("Si eseguono interventi chirurgici open/laparoscopici/endoscopici per:\n" +
                    "- patologia oncologica\n" +
                    "- calcolosi urinaria (ureterorenoscopia e litotrissia percutanea con strumenti semirigidi e flessibili)\n" +
                    "- ipertrofia prostatica benigna\n" +
                    "- patologia andrologica\n" +
                    "\n" +
                    "ATTIVITA’ AMBULATORIALI:\n" +
                    "- Ambulatorio Oncologico Urologico\n" +
                    "- Ambulatorio Calcolosi\n" +
                    "- Ambulatorio Uroginecologico\n" +
                    "- Interventi ambulatoriali di chirurgia minore\n" +
                    "- Visite urologiche (anche di consulenza per il Pronto Soccorso)\n" +
                    "- Medicazioni\n" +
                    "- Uretrocistoscopia\n" +
                    "- Biopsia prostatica\n" +
                    "- Instillazioni intravescicali di antiblastici\n" +
                    "- Cateterizzazioni difficoltose\n" +
                    "- Uroflussometria\n" +
                    "- Trattamenti ESWL\n" +
                    "\n" +
                    "Prenotazione Ambulatorio:\n" +
                    "Effettuata con impegnativa del Medico Curante e dello Specialista presso :\n" +
                    "- sportelli del Centro Unico di Prenotazione (CUP)\n" +
                    "- da telefono fisso e da cellulare 840.709210\n" +
                    "\n" +
                    "Accesso diretto all’Ambulatorio per le visite Urologiche con carattere di urgenza (con impegnativa)\n" +
                    "\n" +
                    "Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "- Week Surgery\n" +
                    "- Prestazioni:\n" +
                    "- Ricovero\n" +
                    "- Day Hospital\n" +
                    "- Day Surgery\n" +
                    "- Week Surgery\n" +
                    "- Litotrizia extracorporea ad onde d'urto (eswl)\n" +
                    "- Cistoscopia\n" +
                    "- Visita ambulatoriale\n" +
                    "\n" +
                    "REFERTI\n" +
                    "Tempi di Consegna:\n" +
                    "Immediata , a fine prestazione presso l’Ambulatorio dove è stata effettuata la stessa.\n" +
                    "(a parte i referti istologici che verranno consegnati alla data stabilita dopo la prestazione)");
            item26.setImage(FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/line.jpg")));
            item26.setAvailable(true);
            post(item26);

        }

        if(cor.findAll().size()==0){
            Date today = new Date();
            List<Item> items = new ArrayList<>();
            items.add(ir.getById(2));
            items.add(ir.getById(3));
            CustomOrder order1 = new CustomOrder(1,20.0, today, ur.findUserByUsername("buyer2"), ur.findUserByUsername("admin"), items);
            cor.save(order1);
        }
    }

    public List<Item> findAll() {
        return ir.findAll();
    }


    public Item get(int id) {
        return ir.findById(id).get();
    }
    public CustomOrder getOrder(int id) {
        return cor.findById(id).get();
    }

    public Item post(Item p) {
        return ir.save(p);
    }

    public Item put(Item p) {
        return ir.save(p);
    }

    public boolean exists(int id) {
        return ir.existsById(id);
    }

    public boolean delete(int id) {
        ir.deleteById(id);
        if (ir.existsById(id)) {
            return false;
        }
        return true;
    }

    public List<SubCategory> getSubCategories(String categoryName) {
        return scr.findAllByParentCategoryName(categoryName);
    }

    public List<Category> getCategories() {
        return cr.findAll();
    }

    public List<User> getAuthors() {
        return ur.findAll();
    }

    public List<Item> searchByCategoryAndType(String category, ItemType type) {
        return ir.findTop20ByCategoryNameIgnoreCaseAndType(category, type);
    }

    public User findUserByUsername(String username) {
        return ur.findUserByUsername(username);
    }

    public void postUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        ur.save(user);
    }

    public List<Item> list(String search) {
        return ir.findTop20ByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(search, search, search);
    }

    public CustomOrder findCustomOrderByIdAndCustomerUsername(int id, String username){

        return cor.findCustomOrderByIdAndCustomerUsername(id,username);
       // for (CustomOrder o : ur.findUserByUsername(username).getOrders()){ if(o.getId()==id) return o;  }
    }

    public List<CustomOrder> findCustomOrdersByCustomerUsername (String username){
        return ur.findUserByUsername(username).getOrders();
    }


    public void addFavoriteToUser(int id, User user) {
        Item item = ir.findById(id).orElse(null);
        if (item != null) {
                user.getFavorites().add(item);
                ur.save(user);

        }
    }

    public void removeFavoriteFromUser(int id, User user) {
        Item item = ir.getById(id);
        user.getFavorites().remove(item);
        ur.save(user);
    }

    public List<User> getAllUserByRole(String roleId) {
        return ur.findAllByRole(rr.findById(roleId).orElse(null));
    }

    public void addItemToCart(int id, User user) {
        Item item = ir.findById(id).orElse(null);
        if (item != null) {
            if (!user.getCart().contains(item)) {
                user.getCart().add(item);
                ur.save(user);
            }
        }
    }

    public void removeItemFromCart(int id, User user) {
        Item item = ir.getById(id);
        user.getCart().remove(item);
        ur.save(user);
    }

    public boolean placeOrder(User user, CustomOrder order) {
        if(!user.getCart().isEmpty()){
            for(Item i : user.getCart()){
                System.out.println(user.getCart().size());
                System.out.println("cart item: "+i.getTitle());
                if(i.isAvailable()){
                    order.addItemToOrder(i);
                    System.out.println("added");
                } else {
                    System.out.println("skipped");
                }
            }
            if(order.getItems().size()==0){
                return false;
            } else {
                order.setCustomerUsername(user);
                cor.save(order);
            }
            user.getCart().clear();
            user.getOrders().add(order);
            ur.save(user);
            return true;
        }
        return false;
    }

    public void importFromFile(MultipartFile csvFile) throws IOException, CsvValidationException, InvalidCategoryException {
        List<Item> items = new ArrayList<>();

        CSVReader csvReader = new CSVReader(new InputStreamReader(csvFile.getInputStream()));

        String[] line;

        int index = 0;

        while ((line = csvReader.readNext()) != null) {
            index++;

            if (index == 1)
                continue;

            String title = line[0];
            double price = Double.parseDouble(line[1]);
            boolean available = Boolean.parseBoolean(line[2]);
            String description = line[3];
            Category category = cr.findById(line[4]).orElse(null);

            if (category == null) {
                throw new InvalidCategoryException("La categoria indicata non esiste");
            }

            SubCategory subCategory = scr.findById(line[5]).orElse(null);
            User author = ur.findUserByUsername(line[6]);
            ItemType type = ItemType.Offerta;

            List<String> imagesLinks = new ArrayList<>();
            for (int i = 8; i < 16; i++) {
                if (line[i].length() > 0)
                    imagesLinks.add(line[i]);
                else
                    break;
            }

            items.add(new Item(title, price, available, description, category, subCategory, author, type, imagesLinks));
        }

        ir.saveAll(items);
    }

}
