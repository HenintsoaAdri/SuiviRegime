/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     16/03/2017 09:40:47                          */
/*==============================================================*/


drop index ALIMENTATION_REGIME_FK;

drop index ALIMENTATION_PK;

drop table ALIMENTATION;

drop index CONSEILALIMENTATION_PK;

drop table ALIMENTATIONCONSEIL;

drop index POIDS_REGIME_FK;

drop index POIDS_PK;

drop table POIDS;

drop index REGIME_UTILISATEUR_FK;

drop index REGIME_PK;

drop table REGIME;

drop index SPORT_PK;

drop table SPORT;

drop index CONSEIL_SPORT_FK;

drop index CONSEILSPORT_PK;

drop table SPORTCONSEIL;

drop index SPORT_REGIME2_FK;

drop index SPORT_REGIME_FK;

drop index SPORT_REGIME_PK;

drop table SPORTREGIME;

drop index UTILISATEUR_PK;

drop table UTILISATEUR;

/*==============================================================*/
/* Table: ALIMENTATION                                          */
/*==============================================================*/
create table ALIMENTATION (
   IDALIMENTATION       SERIAL               not null,
   IDREGIME             INT4                 not null,
   REPASALIMENTATION    VARCHAR(300)         not null,
   BOISSONALIMENTATION  VARCHAR(100)         null,
   PERIODEALIMENTATION  INT2                 not null,
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
/* Table: ALIMENTATIONCONSEIL                                   */
/*==============================================================*/
create table ALIMENTATIONCONSEIL (
   IDALIMENTATIONCONSEIL SERIAL               not null,
   NOMALIMENTATIONCONSEIL VARCHAR(300)         not null,
   MATINALIMENTATIONCONSEIL VARCHAR(500)         not null,
   MIDIALIMENTATIONCONSEIL VARCHAR(500)         not null,
   SOIRALIMENTATIONCONSEIL VARCHAR(500)         not null,
   constraint PK_ALIMENTATIONCONSEIL primary key (IDALIMENTATIONCONSEIL)
);

/*==============================================================*/
/* Index: CONSEILALIMENTATION_PK                                */
/*==============================================================*/
create unique index CONSEILALIMENTATION_PK on ALIMENTATIONCONSEIL (
IDALIMENTATIONCONSEIL
);

/*==============================================================*/
/* Table: POIDS                                                 */
/*==============================================================*/
create table POIDS (
   IDPOIDS              SERIAL               not null,
   IDREGIME             INT4                 not null,
   VALEURPOIDS          INT2                 not null,
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
   DEBUTREGIME          DATE                 not null,
   FINREGIME            DATE                 null,
   POIDSOBJECTIFREGIME  INT2                 null,
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
   LIBELLESPORT         VARCHAR(200)         not null,
   ACTIVITESSPORT       VARCHAR(200)         not null,
   constraint PK_SPORT primary key (IDSPORT)
);

/*==============================================================*/
/* Index: SPORT_PK                                              */
/*==============================================================*/
create unique index SPORT_PK on SPORT (
IDSPORT
);

/*==============================================================*/
/* Table: SPORTCONSEIL                                          */
/*==============================================================*/
create table SPORTCONSEIL (
   IDSPORTCONSEIL       SERIAL               not null,
   IDSPORT              INT4                 not null,
   RYTHMESPORTCONSEIL   INT2                 not null,
   DETAILSPORTCONSEIL   TEXT                 null,
   constraint PK_SPORTCONSEIL primary key (IDSPORTCONSEIL)
);

/*==============================================================*/
/* Index: CONSEILSPORT_PK                                       */
/*==============================================================*/
create unique index CONSEILSPORT_PK on SPORTCONSEIL (
IDSPORTCONSEIL
);

/*==============================================================*/
/* Index: CONSEIL_SPORT_FK                                      */
/*==============================================================*/
create  index CONSEIL_SPORT_FK on SPORTCONSEIL (
IDSPORT
);

/*==============================================================*/
/* Table: SPORTREGIME                                           */
/*==============================================================*/
create table SPORTREGIME (
   IDREGIME             INT4                 not null,
   IDSPORT              INT4                 not null,
   DATESPORTREGIME      DATE                 null,
   RYTHMESPORTREGIME    INT2                 null,
   constraint PK_SPORTREGIME primary key (IDREGIME, IDSPORT)
);

/*==============================================================*/
/* Index: SPORT_REGIME_PK                                       */
/*==============================================================*/
create unique index SPORT_REGIME_PK on SPORTREGIME (
IDREGIME,
IDSPORT
);

/*==============================================================*/
/* Index: SPORT_REGIME_FK                                       */
/*==============================================================*/
create  index SPORT_REGIME_FK on SPORTREGIME (
IDREGIME
);

/*==============================================================*/
/* Index: SPORT_REGIME2_FK                                      */
/*==============================================================*/
create  index SPORT_REGIME2_FK on SPORTREGIME (
IDSPORT
);

/*==============================================================*/
/* Table: UTILISATEUR                                           */
/*==============================================================*/
create table UTILISATEUR (
   IDUTILISATEUR        SERIAL               not null,
   NOMUTILISATEUR       VARCHAR(100)         not null,
   PRENOMUTILISATEUR    VARCHAR(100)         null,
   DATENAISSANCEUTILISATEUR DATE                 not null,
   SEXEUTILISATEUR      VARCHAR(8)           not null,
   IDENTIFIANTUTILISATEUR VARCHAR(25)          not null,
   PASSWORDUTILISATEUR  VARCHAR(25)          not null,
   ADRESSEUTILISATEUR   VARCHAR(200)         null,
   EMAILUTILISATEUR     VARCHAR(50)          not null,
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

alter table POIDS
   add constraint FK_POIDS_POIDS_REG_REGIME foreign key (IDREGIME)
      references REGIME (IDREGIME)
      on delete restrict on update restrict;

alter table REGIME
   add constraint FK_REGIME_REGIME_UT_UTILISAT foreign key (IDUTILISATEUR)
      references UTILISATEUR (IDUTILISATEUR)
      on delete restrict on update restrict;

alter table SPORTCONSEIL
   add constraint FK_SPORTCON_CONSEIL_S_SPORT foreign key (IDSPORT)
      references SPORT (IDSPORT)
      on delete restrict on update restrict;

alter table SPORTREGIME
   add constraint FK_SPORTREG_SPORTREGI_REGIME foreign key (IDREGIME)
      references REGIME (IDREGIME)
      on delete restrict on update restrict;

alter table SPORTREGIME
   add constraint FK_SPORTREG_SPORTREGI_SPORT foreign key (IDSPORT)
      references SPORT (IDSPORT)
      on delete restrict on update restrict;

