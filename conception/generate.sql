/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     05/03/2017 08:43:47                          */
/*==============================================================*/


drop index ALIMENTATION_REGIME_FK;

drop index ALIMENTATION_PK;

drop table ALIMENTATION;

drop index CONSEILALIMENTATION_PK;

drop table CONSEILALIMENTATION;

drop index CONSEIL_SPORT_FK;

drop index CONSEILSPORT_PK;

drop table CONSEILSPORT;

drop index POIDS_REGIME_FK;

drop index POIDS_PK;

drop table POIDS;

drop index REGIME_UTILISATEUR_FK;

drop index REGIME_PK;

drop table REGIME;

drop index SPORT_PK;

drop table SPORT;

drop index SPORT_REGIME2_FK;

drop index SPORT_REGIME_FK;

drop index SPORT_REGIME_PK;

drop table SPORT_REGIME;

drop index UTILISATEUR_PK;

drop table UTILISATEUR;

/*==============================================================*/
/* Table: ALIMENTATION                                          */
/*==============================================================*/
create table ALIMENTATION (
   IDALIMENTATION       SERIAL               not null,
   IDREGIME             INT4                 not null,
   REPAS                VARCHAR(300)         not null,
   BOISSON              VARCHAR(100)         null,
   PERIODE              INT2                 not null,
   DATEALIMENTATION     DATE                 not null,
   constraint PK_ALIMENTATION primary key (IDALIMENTATION)
);

/*==============================================================*/
/* Index: ALIMENTATION_PK                                       */
/*==============================================================*/
create unique index ALIMENTATION_PK on ALIMENTATION (
IDALIMENTATION
);

/*==============================================================*/
/* Index: ALIMENTATION_REGIME_FK                                */
/*==============================================================*/
create  index ALIMENTATION_REGIME_FK on ALIMENTATION (
IDREGIME
);

/*==============================================================*/
/* Table: CONSEILALIMENTATION                                   */
/*==============================================================*/
create table CONSEILALIMENTATION (
   IDCONSEILALIMENTATION SERIAL               not null,
   NOMCONSEILALIMENTATION VARCHAR(300)         not null,
   MATIN                VARCHAR(500)         not null,
   MIDI                 VARCHAR(500)         not null,
   SOIR                 VARCHAR(500)         not null,
   constraint PK_CONSEILALIMENTATION primary key (IDCONSEILALIMENTATION)
);

/*==============================================================*/
/* Index: CONSEILALIMENTATION_PK                                */
/*==============================================================*/
create unique index CONSEILALIMENTATION_PK on CONSEILALIMENTATION (
IDCONSEILALIMENTATION
);

/*==============================================================*/
/* Table: CONSEILSPORT                                          */
/*==============================================================*/
create table CONSEILSPORT (
   IDCONSEILSPORT       SERIAL               not null,
   IDSPORT              INT4                 not null,
   RYTHMECONSEIL        INT2                 not null,
   DETAIL               TEXT                 null,
   constraint PK_CONSEILSPORT primary key (IDCONSEILSPORT)
);

/*==============================================================*/
/* Index: CONSEILSPORT_PK                                       */
/*==============================================================*/
create unique index CONSEILSPORT_PK on CONSEILSPORT (
IDCONSEILSPORT
);

/*==============================================================*/
/* Index: CONSEIL_SPORT_FK                                      */
/*==============================================================*/
create  index CONSEIL_SPORT_FK on CONSEILSPORT (
IDSPORT
);

/*==============================================================*/
/* Table: POIDS                                                 */
/*==============================================================*/
create table POIDS (
   IDPOIDS              SERIAL               not null,
   IDREGIME             INT4                 not null,
   POIDS                INT2                 not null,
   DATEPOIDS            DATE                 not null,
   constraint PK_POIDS primary key (IDPOIDS)
);

/*==============================================================*/
/* Index: POIDS_PK                                              */
/*==============================================================*/
create unique index POIDS_PK on POIDS (
IDPOIDS
);

/*==============================================================*/
/* Index: POIDS_REGIME_FK                                       */
/*==============================================================*/
create  index POIDS_REGIME_FK on POIDS (
IDREGIME
);

/*==============================================================*/
/* Table: REGIME                                                */
/*==============================================================*/
create table REGIME (
   IDREGIME             SERIAL               not null,
   IDUTILISATEUR        INT4                 not null,
   DATEDEBUT            DATE                 not null,
   DATEFIN              DATE                 null,
   POIDSOBJECTIF        INT2                 null,
   constraint PK_REGIME primary key (IDREGIME)
);

/*==============================================================*/
/* Index: REGIME_PK                                             */
/*==============================================================*/
create unique index REGIME_PK on REGIME (
IDREGIME
);

/*==============================================================*/
/* Index: REGIME_UTILISATEUR_FK                                 */
/*==============================================================*/
create  index REGIME_UTILISATEUR_FK on REGIME (
IDUTILISATEUR
);

/*==============================================================*/
/* Table: SPORT                                                 */
/*==============================================================*/
create table SPORT (
   IDSPORT              SERIAL               not null,
   SPORT                VARCHAR(200)         not null,
   ACTIVITES            VARCHAR(200)         not null,
   constraint PK_SPORT primary key (IDSPORT)
);

/*==============================================================*/
/* Index: SPORT_PK                                              */
/*==============================================================*/
create unique index SPORT_PK on SPORT (
IDSPORT
);

/*==============================================================*/
/* Table: SPORT_REGIME                                          */
/*==============================================================*/
create table SPORT_REGIME (
   IDREGIME             INT4                 not null,
   IDSPORT              INT4                 not null,
   DATESPORT            DATE                 null,
   RYTHMESPORT          INT2                 null,
   constraint PK_SPORT_REGIME primary key (IDREGIME, IDSPORT)
);

/*==============================================================*/
/* Index: SPORT_REGIME_PK                                       */
/*==============================================================*/
create unique index SPORT_REGIME_PK on SPORT_REGIME (
IDREGIME,
IDSPORT
);

/*==============================================================*/
/* Index: SPORT_REGIME_FK                                       */
/*==============================================================*/
create  index SPORT_REGIME_FK on SPORT_REGIME (
IDREGIME
);

/*==============================================================*/
/* Index: SPORT_REGIME2_FK                                      */
/*==============================================================*/
create  index SPORT_REGIME2_FK on SPORT_REGIME (
IDSPORT
);

/*==============================================================*/
/* Table: UTILISATEUR                                           */
/*==============================================================*/
create table UTILISATEUR (
   IDUTILISATEUR        SERIAL               not null,
   NOM                  VARCHAR(100)         not null,
   PRENOM               VARCHAR(100)         null,
   DATENAISSANCE        DATE                 not null,
   SEXE                 VARCHAR(8)           not null,
   IDENTIFIANT          VARCHAR(25)          not null,
   PASSWORD             VARCHAR(25)          not null,
   ADRESSE              VARCHAR(200)         null,
   EMAIL                VARCHAR(50)          not null,
   constraint PK_UTILISATEUR primary key (IDUTILISATEUR)
);

/*==============================================================*/
/* Index: UTILISATEUR_PK                                        */
/*==============================================================*/
create unique index UTILISATEUR_PK on UTILISATEUR (
IDUTILISATEUR
);

alter table ALIMENTATION
   add constraint FK_ALIMENTA_ALIMENTAT_REGIME foreign key (IDREGIME)
      references REGIME (IDREGIME)
      on delete restrict on update restrict;

alter table CONSEILSPORT
   add constraint FK_CONSEILS_CONSEIL_S_SPORT foreign key (IDSPORT)
      references SPORT (IDSPORT)
      on delete restrict on update restrict;

alter table POIDS
   add constraint FK_POIDS_POIDS_REG_REGIME foreign key (IDREGIME)
      references REGIME (IDREGIME)
      on delete restrict on update restrict;

alter table REGIME
   add constraint FK_REGIME_REGIME_UT_UTILISAT foreign key (IDUTILISATEUR)
      references UTILISATEUR (IDUTILISATEUR)
      on delete restrict on update restrict;

alter table SPORT_REGIME
   add constraint FK_SPORT_RE_SPORT_REG_REGIME foreign key (IDREGIME)
      references REGIME (IDREGIME)
      on delete restrict on update restrict;

alter table SPORT_REGIME
   add constraint FK_SPORT_RE_SPORT_REG_SPORT foreign key (IDSPORT)
      references SPORT (IDSPORT)
      on delete restrict on update restrict;

