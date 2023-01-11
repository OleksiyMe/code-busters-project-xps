
package com.cydeo.dto.feign;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AED",
    "AFN",
    "ALL",
    "AMD",
    "ANG",
    "AOA",
    "ARS",
    "AUD",
    "AWG",
    "AZN",
    "BAM",
    "BBD",
    "BDT",
    "BGN",
    "BHD",
    "BIF",
    "BMD",
    "BND",
    "BOB",
    "BRL",
    "BSD",
    "BTC",
    "BTN",
    "BWP",
    "BYN",
    "BZD",
    "CAD",
    "CDF",
    "CHF",
    "CLF",
    "CLP",
    "CNH",
    "CNY",
    "COP",
    "CRC",
    "CUC",
    "CUP",
    "CVE",
    "CZK",
    "DJF",
    "DKK",
    "DOP",
    "DZD",
    "EGP",
    "ERN",
    "ETB",
    "EUR",
    "FJD",
    "FKP",
    "GBP",
    "GEL",
    "GGP",
    "GHS",
    "GIP",
    "GMD",
    "GNF",
    "GTQ",
    "GYD",
    "HKD",
    "HNL",
    "HRK",
    "HTG",
    "HUF",
    "IDR",
    "ILS",
    "IMP",
    "INR",
    "IQD",
    "IRR",
    "ISK",
    "JEP",
    "JMD",
    "JOD",
    "JPY",
    "KES",
    "KGS",
    "KHR",
    "KMF",
    "KPW",
    "KRW",
    "KWD",
    "KYD",
    "KZT",
    "LAK",
    "LBP",
    "LKR",
    "LRD",
    "LSL",
    "LYD",
    "MAD",
    "MDL",
    "MGA",
    "MKD",
    "MMK",
    "MNT",
    "MOP",
    "MRU",
    "MUR",
    "MVR",
    "MWK",
    "MXN",
    "MYR",
    "MZN",
    "NAD",
    "NGN",
    "NIO",
    "NOK",
    "NPR",
    "NZD",
    "OMR",
    "PAB",
    "PEN",
    "PGK",
    "PHP",
    "PKR",
    "PLN",
    "PYG",
    "QAR",
    "RON",
    "RSD",
    "RUB",
    "RWF",
    "SAR",
    "SBD",
    "SCR",
    "SDG",
    "SEK",
    "SGD",
    "SHP",
    "SLL",
    "SOS",
    "SRD",
    "SSP",
    "STD",
    "STN",
    "SVC",
    "SYP",
    "SZL",
    "THB",
    "TJS",
    "TMT",
    "TND",
    "TOP",
    "TRY",
    "TTD",
    "TWD",
    "TZS",
    "UAH",
    "UGX",
    "USD",
    "UYU",
    "UZS",
    "VES",
    "VND",
    "VUV",
    "WST",
    "XAF",
    "XAG",
    "XAU",
    "XCD",
    "XDR",
    "XOF",
    "XPD",
    "XPF",
    "XPT",
    "YER",
    "ZAR",
    "ZMW",
    "ZWL"
})
@Generated("jsonschema2pojo")
public class Rates {

    @JsonProperty("AED")
    private BigDecimal aed;
    @JsonProperty("AFN")
    private BigDecimal afn;
    @JsonProperty("ALL")
    private BigDecimal all;
    @JsonProperty("AMD")
    private BigDecimal amd;
    @JsonProperty("ANG")
    private BigDecimal ang;
    @JsonProperty("AOA")
    private BigDecimal aoa;
    @JsonProperty("ARS")
    private BigDecimal ars;
    @JsonProperty("AUD")
    private BigDecimal aud;
    @JsonProperty("AWG")
    private BigDecimal awg;
    @JsonProperty("AZN")
    private BigDecimal azn;
    @JsonProperty("BAM")
    private BigDecimal bam;
    @JsonProperty("BBD")
    private BigDecimal bbd;
    @JsonProperty("BDT")
    private BigDecimal bdt;
    @JsonProperty("BGN")
    private BigDecimal bgn;
    @JsonProperty("BHD")
    private BigDecimal bhd;
    @JsonProperty("BIF")
    private BigDecimal bif;
    @JsonProperty("BMD")
    private BigDecimal bmd;
    @JsonProperty("BND")
    private BigDecimal bnd;
    @JsonProperty("BOB")
    private BigDecimal bob;
    @JsonProperty("BRL")
    private BigDecimal brl;
    @JsonProperty("BSD")
    private BigDecimal bsd;
    @JsonProperty("BTC")
    private BigDecimal btc;
    @JsonProperty("BTN")
    private BigDecimal btn;
    @JsonProperty("BWP")
    private BigDecimal bwp;
    @JsonProperty("BYN")
    private BigDecimal byn;
    @JsonProperty("BZD")
    private BigDecimal bzd;
    @JsonProperty("CAD")
    private BigDecimal cad;
    @JsonProperty("CDF")
    private BigDecimal cdf;
    @JsonProperty("CHF")
    private BigDecimal chf;
    @JsonProperty("CLF")
    private BigDecimal clf;
    @JsonProperty("CLP")
    private BigDecimal clp;
    @JsonProperty("CNH")
    private BigDecimal cnh;
    @JsonProperty("CNY")
    private BigDecimal cny;
    @JsonProperty("COP")
    private BigDecimal cop;
    @JsonProperty("CRC")
    private BigDecimal crc;
    @JsonProperty("CUC")
    private BigDecimal cuc;
    @JsonProperty("CUP")
    private BigDecimal cup;
    @JsonProperty("CVE")
    private BigDecimal cve;
    @JsonProperty("CZK")
    private BigDecimal czk;
    @JsonProperty("DJF")
    private BigDecimal djf;
    @JsonProperty("DKK")
    private BigDecimal dkk;
    @JsonProperty("DOP")
    private BigDecimal dop;
    @JsonProperty("DZD")
    private BigDecimal dzd;
    @JsonProperty("EGP")
    private BigDecimal egp;
    @JsonProperty("ERN")
    private BigDecimal ern;
    @JsonProperty("ETB")
    private BigDecimal etb;
    @JsonProperty("EUR")
    private BigDecimal eur;
    @JsonProperty("FJD")
    private BigDecimal fjd;
    @JsonProperty("FKP")
    private BigDecimal fkp;
    @JsonProperty("GBP")
    private BigDecimal gbp;
    @JsonProperty("GEL")
    private BigDecimal gel;
    @JsonProperty("GGP")
    private BigDecimal ggp;
    @JsonProperty("GHS")
    private BigDecimal ghs;
    @JsonProperty("GIP")
    private BigDecimal gip;
    @JsonProperty("GMD")
    private BigDecimal gmd;
    @JsonProperty("GNF")
    private BigDecimal gnf;
    @JsonProperty("GTQ")
    private BigDecimal gtq;
    @JsonProperty("GYD")
    private BigDecimal gyd;
    @JsonProperty("HKD")
    private BigDecimal hkd;
    @JsonProperty("HNL")
    private BigDecimal hnl;
    @JsonProperty("HRK")
    private BigDecimal hrk;
    @JsonProperty("HTG")
    private BigDecimal htg;
    @JsonProperty("HUF")
    private BigDecimal huf;
    @JsonProperty("IDR")
    private BigDecimal idr;
    @JsonProperty("ILS")
    private BigDecimal ils;
    @JsonProperty("IMP")
    private BigDecimal imp;
    @JsonProperty("INR")
    private BigDecimal inr;
    @JsonProperty("IQD")
    private BigDecimal iqd;
    @JsonProperty("IRR")
    private BigDecimal irr;
    @JsonProperty("ISK")
    private BigDecimal isk;
    @JsonProperty("JEP")
    private BigDecimal jep;
    @JsonProperty("JMD")
    private BigDecimal jmd;
    @JsonProperty("JOD")
    private BigDecimal jod;
    @JsonProperty("JPY")
    private BigDecimal jpy;
    @JsonProperty("KES")
    private BigDecimal kes;
    @JsonProperty("KGS")
    private BigDecimal kgs;
    @JsonProperty("KHR")
    private BigDecimal khr;
    @JsonProperty("KMF")
    private BigDecimal kmf;
    @JsonProperty("KPW")
    private BigDecimal kpw;
    @JsonProperty("KRW")
    private BigDecimal krw;
    @JsonProperty("KWD")
    private BigDecimal kwd;
    @JsonProperty("KYD")
    private BigDecimal kyd;
    @JsonProperty("KZT")
    private BigDecimal kzt;
    @JsonProperty("LAK")
    private BigDecimal lak;
    @JsonProperty("LBP")
    private BigDecimal lbp;
    @JsonProperty("LKR")
    private BigDecimal lkr;
    @JsonProperty("LRD")
    private BigDecimal lrd;
    @JsonProperty("LSL")
    private BigDecimal lsl;
    @JsonProperty("LYD")
    private BigDecimal lyd;
    @JsonProperty("MAD")
    private BigDecimal mad;
    @JsonProperty("MDL")
    private BigDecimal mdl;
    @JsonProperty("MGA")
    private BigDecimal mga;
    @JsonProperty("MKD")
    private BigDecimal mkd;
    @JsonProperty("MMK")
    private BigDecimal mmk;
    @JsonProperty("MNT")
    private BigDecimal mnt;
    @JsonProperty("MOP")
    private BigDecimal mop;
    @JsonProperty("MRU")
    private BigDecimal mru;
    @JsonProperty("MUR")
    private BigDecimal mur;
    @JsonProperty("MVR")
    private BigDecimal mvr;
    @JsonProperty("MWK")
    private BigDecimal mwk;
    @JsonProperty("MXN")
    private BigDecimal mxn;
    @JsonProperty("MYR")
    private BigDecimal myr;
    @JsonProperty("MZN")
    private BigDecimal mzn;
    @JsonProperty("NAD")
    private BigDecimal nad;
    @JsonProperty("NGN")
    private BigDecimal ngn;
    @JsonProperty("NIO")
    private BigDecimal nio;
    @JsonProperty("NOK")
    private BigDecimal nok;
    @JsonProperty("NPR")
    private BigDecimal npr;
    @JsonProperty("NZD")
    private BigDecimal nzd;
    @JsonProperty("OMR")
    private BigDecimal omr;
    @JsonProperty("PAB")
    private BigDecimal pab;
    @JsonProperty("PEN")
    private BigDecimal pen;
    @JsonProperty("PGK")
    private BigDecimal pgk;
    @JsonProperty("PHP")
    private BigDecimal php;
    @JsonProperty("PKR")
    private BigDecimal pkr;
    @JsonProperty("PLN")
    private BigDecimal pln;
    @JsonProperty("PYG")
    private BigDecimal pyg;
    @JsonProperty("QAR")
    private BigDecimal qar;
    @JsonProperty("RON")
    private BigDecimal ron;
    @JsonProperty("RSD")
    private BigDecimal rsd;
    @JsonProperty("RUB")
    private BigDecimal rub;
    @JsonProperty("RWF")
    private BigDecimal rwf;
    @JsonProperty("SAR")
    private BigDecimal sar;
    @JsonProperty("SBD")
    private BigDecimal sbd;
    @JsonProperty("SCR")
    private BigDecimal scr;
    @JsonProperty("SDG")
    private BigDecimal sdg;
    @JsonProperty("SEK")
    private BigDecimal sek;
    @JsonProperty("SGD")
    private BigDecimal sgd;
    @JsonProperty("SHP")
    private BigDecimal shp;
    @JsonProperty("SLL")
    private BigDecimal sll;
    @JsonProperty("SOS")
    private BigDecimal sos;
    @JsonProperty("SRD")
    private BigDecimal srd;
    @JsonProperty("SSP")
    private BigDecimal ssp;
    @JsonProperty("STD")
    private BigDecimal std;
    @JsonProperty("STN")
    private BigDecimal stn;
    @JsonProperty("SVC")
    private BigDecimal svc;
    @JsonProperty("SYP")
    private BigDecimal syp;
    @JsonProperty("SZL")
    private BigDecimal szl;
    @JsonProperty("THB")
    private BigDecimal thb;
    @JsonProperty("TJS")
    private BigDecimal tjs;
    @JsonProperty("TMT")
    private BigDecimal tmt;
    @JsonProperty("TND")
    private BigDecimal tnd;
    @JsonProperty("TOP")
    private BigDecimal top;
    @JsonProperty("TRY")
    private BigDecimal _try;
    @JsonProperty("TTD")
    private BigDecimal ttd;
    @JsonProperty("TWD")
    private BigDecimal twd;
    @JsonProperty("TZS")
    private BigDecimal tzs;
    @JsonProperty("UAH")
    private BigDecimal uah;
    @JsonProperty("UGX")
    private BigDecimal ugx;
    @JsonProperty("USD")
    private BigDecimal usd;
    @JsonProperty("UYU")
    private BigDecimal uyu;
    @JsonProperty("UZS")
    private BigDecimal uzs;
    @JsonProperty("VES")
    private BigDecimal ves;
    @JsonProperty("VND")
    private BigDecimal vnd;
    @JsonProperty("VUV")
    private BigDecimal vuv;
    @JsonProperty("WST")
    private BigDecimal wst;
    @JsonProperty("XAF")
    private BigDecimal xaf;
    @JsonProperty("XAG")
    private BigDecimal xag;
    @JsonProperty("XAU")
    private BigDecimal xau;
    @JsonProperty("XCD")
    private BigDecimal xcd;
    @JsonProperty("XDR")
    private BigDecimal xdr;
    @JsonProperty("XOF")
    private BigDecimal xof;
    @JsonProperty("XPD")
    private BigDecimal xpd;
    @JsonProperty("XPF")
    private BigDecimal xpf;
    @JsonProperty("XPT")
    private BigDecimal xpt;
    @JsonProperty("YER")
    private BigDecimal yer;
    @JsonProperty("ZAR")
    private BigDecimal zar;
    @JsonProperty("ZMW")
    private BigDecimal zmw;
    @JsonProperty("ZWL")
    private BigDecimal zwl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AED")
    public BigDecimal getAed() {
        return aed;
    }

    @JsonProperty("AED")
    public void setAed(BigDecimal aed) {
        this.aed = aed;
    }

    @JsonProperty("AFN")
    public BigDecimal getAfn() {
        return afn;
    }

    @JsonProperty("AFN")
    public void setAfn(BigDecimal afn) {
        this.afn = afn;
    }

    @JsonProperty("ALL")
    public BigDecimal getAll() {
        return all;
    }

    @JsonProperty("ALL")
    public void setAll(BigDecimal all) {
        this.all = all;
    }

    @JsonProperty("AMD")
    public BigDecimal getAmd() {
        return amd;
    }

    @JsonProperty("AMD")
    public void setAmd(BigDecimal amd) {
        this.amd = amd;
    }

    @JsonProperty("ANG")
    public BigDecimal getAng() {
        return ang;
    }

    @JsonProperty("ANG")
    public void setAng(BigDecimal ang) {
        this.ang = ang;
    }

    @JsonProperty("AOA")
    public BigDecimal getAoa() {
        return aoa;
    }

    @JsonProperty("AOA")
    public void setAoa(BigDecimal aoa) {
        this.aoa = aoa;
    }

    @JsonProperty("ARS")
    public BigDecimal getArs() {
        return ars;
    }

    @JsonProperty("ARS")
    public void setArs(BigDecimal ars) {
        this.ars = ars;
    }

    @JsonProperty("AUD")
    public BigDecimal getAud() {
        return aud;
    }

    @JsonProperty("AUD")
    public void setAud(BigDecimal aud) {
        this.aud = aud;
    }

    @JsonProperty("AWG")
    public BigDecimal getAwg() {
        return awg;
    }

    @JsonProperty("AWG")
    public void setAwg(BigDecimal awg) {
        this.awg = awg;
    }

    @JsonProperty("AZN")
    public BigDecimal getAzn() {
        return azn;
    }

    @JsonProperty("AZN")
    public void setAzn(BigDecimal azn) {
        this.azn = azn;
    }

    @JsonProperty("BAM")
    public BigDecimal getBam() {
        return bam;
    }

    @JsonProperty("BAM")
    public void setBam(BigDecimal bam) {
        this.bam = bam;
    }

    @JsonProperty("BBD")
    public BigDecimal getBbd() {
        return bbd;
    }

    @JsonProperty("BBD")
    public void setBbd(BigDecimal bbd) {
        this.bbd = bbd;
    }

    @JsonProperty("BDT")
    public BigDecimal getBdt() {
        return bdt;
    }

    @JsonProperty("BDT")
    public void setBdt(BigDecimal bdt) {
        this.bdt = bdt;
    }

    @JsonProperty("BGN")
    public BigDecimal getBgn() {
        return bgn;
    }

    @JsonProperty("BGN")
    public void setBgn(BigDecimal bgn) {
        this.bgn = bgn;
    }

    @JsonProperty("BHD")
    public BigDecimal getBhd() {
        return bhd;
    }

    @JsonProperty("BHD")
    public void setBhd(BigDecimal bhd) {
        this.bhd = bhd;
    }

    @JsonProperty("BIF")
    public BigDecimal getBif() {
        return bif;
    }

    @JsonProperty("BIF")
    public void setBif(BigDecimal bif) {
        this.bif = bif;
    }

    @JsonProperty("BMD")
    public BigDecimal getBmd() {
        return bmd;
    }

    @JsonProperty("BMD")
    public void setBmd(BigDecimal bmd) {
        this.bmd = bmd;
    }

    @JsonProperty("BND")
    public BigDecimal getBnd() {
        return bnd;
    }

    @JsonProperty("BND")
    public void setBnd(BigDecimal bnd) {
        this.bnd = bnd;
    }

    @JsonProperty("BOB")
    public BigDecimal getBob() {
        return bob;
    }

    @JsonProperty("BOB")
    public void setBob(BigDecimal bob) {
        this.bob = bob;
    }

    @JsonProperty("BRL")
    public BigDecimal getBrl() {
        return brl;
    }

    @JsonProperty("BRL")
    public void setBrl(BigDecimal brl) {
        this.brl = brl;
    }

    @JsonProperty("BSD")
    public BigDecimal getBsd() {
        return bsd;
    }

    @JsonProperty("BSD")
    public void setBsd(BigDecimal bsd) {
        this.bsd = bsd;
    }

    @JsonProperty("BTC")
    public BigDecimal getBtc() {
        return btc;
    }

    @JsonProperty("BTC")
    public void setBtc(BigDecimal btc) {
        this.btc = btc;
    }

    @JsonProperty("BTN")
    public BigDecimal getBtn() {
        return btn;
    }

    @JsonProperty("BTN")
    public void setBtn(BigDecimal btn) {
        this.btn = btn;
    }

    @JsonProperty("BWP")
    public BigDecimal getBwp() {
        return bwp;
    }

    @JsonProperty("BWP")
    public void setBwp(BigDecimal bwp) {
        this.bwp = bwp;
    }

    @JsonProperty("BYN")
    public BigDecimal getByn() {
        return byn;
    }

    @JsonProperty("BYN")
    public void setByn(BigDecimal byn) {
        this.byn = byn;
    }

    @JsonProperty("BZD")
    public BigDecimal getBzd() {
        return bzd;
    }

    @JsonProperty("BZD")
    public void setBzd(BigDecimal bzd) {
        this.bzd = bzd;
    }

    @JsonProperty("CAD")
    public BigDecimal getCad() {
        return cad;
    }

    @JsonProperty("CAD")
    public void setCad(BigDecimal cad) {
        this.cad = cad;
    }

    @JsonProperty("CDF")
    public BigDecimal getCdf() {
        return cdf;
    }

    @JsonProperty("CDF")
    public void setCdf(BigDecimal cdf) {
        this.cdf = cdf;
    }

    @JsonProperty("CHF")
    public BigDecimal getChf() {
        return chf;
    }

    @JsonProperty("CHF")
    public void setChf(BigDecimal chf) {
        this.chf = chf;
    }

    @JsonProperty("CLF")
    public BigDecimal getClf() {
        return clf;
    }

    @JsonProperty("CLF")
    public void setClf(BigDecimal clf) {
        this.clf = clf;
    }

    @JsonProperty("CLP")
    public BigDecimal getClp() {
        return clp;
    }

    @JsonProperty("CLP")
    public void setClp(BigDecimal clp) {
        this.clp = clp;
    }

    @JsonProperty("CNH")
    public BigDecimal getCnh() {
        return cnh;
    }

    @JsonProperty("CNH")
    public void setCnh(BigDecimal cnh) {
        this.cnh = cnh;
    }

    @JsonProperty("CNY")
    public BigDecimal getCny() {
        return cny;
    }

    @JsonProperty("CNY")
    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    @JsonProperty("COP")
    public BigDecimal getCop() {
        return cop;
    }

    @JsonProperty("COP")
    public void setCop(BigDecimal cop) {
        this.cop = cop;
    }

    @JsonProperty("CRC")
    public BigDecimal getCrc() {
        return crc;
    }

    @JsonProperty("CRC")
    public void setCrc(BigDecimal crc) {
        this.crc = crc;
    }

    @JsonProperty("CUC")
    public BigDecimal getCuc() {
        return cuc;
    }

    @JsonProperty("CUC")
    public void setCuc(BigDecimal cuc) {
        this.cuc = cuc;
    }

    @JsonProperty("CUP")
    public BigDecimal getCup() {
        return cup;
    }

    @JsonProperty("CUP")
    public void setCup(BigDecimal cup) {
        this.cup = cup;
    }

    @JsonProperty("CVE")
    public BigDecimal getCve() {
        return cve;
    }

    @JsonProperty("CVE")
    public void setCve(BigDecimal cve) {
        this.cve = cve;
    }

    @JsonProperty("CZK")
    public BigDecimal getCzk() {
        return czk;
    }

    @JsonProperty("CZK")
    public void setCzk(BigDecimal czk) {
        this.czk = czk;
    }

    @JsonProperty("DJF")
    public BigDecimal getDjf() {
        return djf;
    }

    @JsonProperty("DJF")
    public void setDjf(BigDecimal djf) {
        this.djf = djf;
    }

    @JsonProperty("DKK")
    public BigDecimal getDkk() {
        return dkk;
    }

    @JsonProperty("DKK")
    public void setDkk(BigDecimal dkk) {
        this.dkk = dkk;
    }

    @JsonProperty("DOP")
    public BigDecimal getDop() {
        return dop;
    }

    @JsonProperty("DOP")
    public void setDop(BigDecimal dop) {
        this.dop = dop;
    }

    @JsonProperty("DZD")
    public BigDecimal getDzd() {
        return dzd;
    }

    @JsonProperty("DZD")
    public void setDzd(BigDecimal dzd) {
        this.dzd = dzd;
    }

    @JsonProperty("EGP")
    public BigDecimal getEgp() {
        return egp;
    }

    @JsonProperty("EGP")
    public void setEgp(BigDecimal egp) {
        this.egp = egp;
    }

    @JsonProperty("ERN")
    public BigDecimal getErn() {
        return ern;
    }

    @JsonProperty("ERN")
    public void setErn(BigDecimal ern) {
        this.ern = ern;
    }

    @JsonProperty("ETB")
    public BigDecimal getEtb() {
        return etb;
    }

    @JsonProperty("ETB")
    public void setEtb(BigDecimal etb) {
        this.etb = etb;
    }

    @JsonProperty("EUR")
    public BigDecimal getEur() {
        return eur;
    }

    @JsonProperty("EUR")
    public void setEur(BigDecimal eur) {
        this.eur = eur;
    }

    @JsonProperty("FJD")
    public BigDecimal getFjd() {
        return fjd;
    }

    @JsonProperty("FJD")
    public void setFjd(BigDecimal fjd) {
        this.fjd = fjd;
    }

    @JsonProperty("FKP")
    public BigDecimal getFkp() {
        return fkp;
    }

    @JsonProperty("FKP")
    public void setFkp(BigDecimal fkp) {
        this.fkp = fkp;
    }

    @JsonProperty("GBP")
    public BigDecimal getGbp() {
        return gbp;
    }

    @JsonProperty("GBP")
    public void setGbp(BigDecimal gbp) {
        this.gbp = gbp;
    }

    @JsonProperty("GEL")
    public BigDecimal getGel() {
        return gel;
    }

    @JsonProperty("GEL")
    public void setGel(BigDecimal gel) {
        this.gel = gel;
    }

    @JsonProperty("GGP")
    public BigDecimal getGgp() {
        return ggp;
    }

    @JsonProperty("GGP")
    public void setGgp(BigDecimal ggp) {
        this.ggp = ggp;
    }

    @JsonProperty("GHS")
    public BigDecimal getGhs() {
        return ghs;
    }

    @JsonProperty("GHS")
    public void setGhs(BigDecimal ghs) {
        this.ghs = ghs;
    }

    @JsonProperty("GIP")
    public BigDecimal getGip() {
        return gip;
    }

    @JsonProperty("GIP")
    public void setGip(BigDecimal gip) {
        this.gip = gip;
    }

    @JsonProperty("GMD")
    public BigDecimal getGmd() {
        return gmd;
    }

    @JsonProperty("GMD")
    public void setGmd(BigDecimal gmd) {
        this.gmd = gmd;
    }

    @JsonProperty("GNF")
    public BigDecimal getGnf() {
        return gnf;
    }

    @JsonProperty("GNF")
    public void setGnf(BigDecimal gnf) {
        this.gnf = gnf;
    }

    @JsonProperty("GTQ")
    public BigDecimal getGtq() {
        return gtq;
    }

    @JsonProperty("GTQ")
    public void setGtq(BigDecimal gtq) {
        this.gtq = gtq;
    }

    @JsonProperty("GYD")
    public BigDecimal getGyd() {
        return gyd;
    }

    @JsonProperty("GYD")
    public void setGyd(BigDecimal gyd) {
        this.gyd = gyd;
    }

    @JsonProperty("HKD")
    public BigDecimal getHkd() {
        return hkd;
    }

    @JsonProperty("HKD")
    public void setHkd(BigDecimal hkd) {
        this.hkd = hkd;
    }

    @JsonProperty("HNL")
    public BigDecimal getHnl() {
        return hnl;
    }

    @JsonProperty("HNL")
    public void setHnl(BigDecimal hnl) {
        this.hnl = hnl;
    }

    @JsonProperty("HRK")
    public BigDecimal getHrk() {
        return hrk;
    }

    @JsonProperty("HRK")
    public void setHrk(BigDecimal hrk) {
        this.hrk = hrk;
    }

    @JsonProperty("HTG")
    public BigDecimal getHtg() {
        return htg;
    }

    @JsonProperty("HTG")
    public void setHtg(BigDecimal htg) {
        this.htg = htg;
    }

    @JsonProperty("HUF")
    public BigDecimal getHuf() {
        return huf;
    }

    @JsonProperty("HUF")
    public void setHuf(BigDecimal huf) {
        this.huf = huf;
    }

    @JsonProperty("IDR")
    public BigDecimal getIdr() {
        return idr;
    }

    @JsonProperty("IDR")
    public void setIdr(BigDecimal idr) {
        this.idr = idr;
    }

    @JsonProperty("ILS")
    public BigDecimal getIls() {
        return ils;
    }

    @JsonProperty("ILS")
    public void setIls(BigDecimal ils) {
        this.ils = ils;
    }

    @JsonProperty("IMP")
    public BigDecimal getImp() {
        return imp;
    }

    @JsonProperty("IMP")
    public void setImp(BigDecimal imp) {
        this.imp = imp;
    }

    @JsonProperty("INR")
    public BigDecimal getInr() {
        return inr;
    }

    @JsonProperty("INR")
    public void setInr(BigDecimal inr) {
        this.inr = inr;
    }

    @JsonProperty("IQD")
    public BigDecimal getIqd() {
        return iqd;
    }

    @JsonProperty("IQD")
    public void setIqd(BigDecimal iqd) {
        this.iqd = iqd;
    }

    @JsonProperty("IRR")
    public BigDecimal getIrr() {
        return irr;
    }

    @JsonProperty("IRR")
    public void setIrr(BigDecimal irr) {
        this.irr = irr;
    }

    @JsonProperty("ISK")
    public BigDecimal getIsk() {
        return isk;
    }

    @JsonProperty("ISK")
    public void setIsk(BigDecimal isk) {
        this.isk = isk;
    }

    @JsonProperty("JEP")
    public BigDecimal getJep() {
        return jep;
    }

    @JsonProperty("JEP")
    public void setJep(BigDecimal jep) {
        this.jep = jep;
    }

    @JsonProperty("JMD")
    public BigDecimal getJmd() {
        return jmd;
    }

    @JsonProperty("JMD")
    public void setJmd(BigDecimal jmd) {
        this.jmd = jmd;
    }

    @JsonProperty("JOD")
    public BigDecimal getJod() {
        return jod;
    }

    @JsonProperty("JOD")
    public void setJod(BigDecimal jod) {
        this.jod = jod;
    }

    @JsonProperty("JPY")
    public BigDecimal getJpy() {
        return jpy;
    }

    @JsonProperty("JPY")
    public void setJpy(BigDecimal jpy) {
        this.jpy = jpy;
    }

    @JsonProperty("KES")
    public BigDecimal getKes() {
        return kes;
    }

    @JsonProperty("KES")
    public void setKes(BigDecimal kes) {
        this.kes = kes;
    }

    @JsonProperty("KGS")
    public BigDecimal getKgs() {
        return kgs;
    }

    @JsonProperty("KGS")
    public void setKgs(BigDecimal kgs) {
        this.kgs = kgs;
    }

    @JsonProperty("KHR")
    public BigDecimal getKhr() {
        return khr;
    }

    @JsonProperty("KHR")
    public void setKhr(BigDecimal khr) {
        this.khr = khr;
    }

    @JsonProperty("KMF")
    public BigDecimal getKmf() {
        return kmf;
    }

    @JsonProperty("KMF")
    public void setKmf(BigDecimal kmf) {
        this.kmf = kmf;
    }

    @JsonProperty("KPW")
    public BigDecimal getKpw() {
        return kpw;
    }

    @JsonProperty("KPW")
    public void setKpw(BigDecimal kpw) {
        this.kpw = kpw;
    }

    @JsonProperty("KRW")
    public BigDecimal getKrw() {
        return krw;
    }

    @JsonProperty("KRW")
    public void setKrw(BigDecimal krw) {
        this.krw = krw;
    }

    @JsonProperty("KWD")
    public BigDecimal getKwd() {
        return kwd;
    }

    @JsonProperty("KWD")
    public void setKwd(BigDecimal kwd) {
        this.kwd = kwd;
    }

    @JsonProperty("KYD")
    public BigDecimal getKyd() {
        return kyd;
    }

    @JsonProperty("KYD")
    public void setKyd(BigDecimal kyd) {
        this.kyd = kyd;
    }

    @JsonProperty("KZT")
    public BigDecimal getKzt() {
        return kzt;
    }

    @JsonProperty("KZT")
    public void setKzt(BigDecimal kzt) {
        this.kzt = kzt;
    }

    @JsonProperty("LAK")
    public BigDecimal getLak() {
        return lak;
    }

    @JsonProperty("LAK")
    public void setLak(BigDecimal lak) {
        this.lak = lak;
    }

    @JsonProperty("LBP")
    public BigDecimal getLbp() {
        return lbp;
    }

    @JsonProperty("LBP")
    public void setLbp(BigDecimal lbp) {
        this.lbp = lbp;
    }

    @JsonProperty("LKR")
    public BigDecimal getLkr() {
        return lkr;
    }

    @JsonProperty("LKR")
    public void setLkr(BigDecimal lkr) {
        this.lkr = lkr;
    }

    @JsonProperty("LRD")
    public BigDecimal getLrd() {
        return lrd;
    }

    @JsonProperty("LRD")
    public void setLrd(BigDecimal lrd) {
        this.lrd = lrd;
    }

    @JsonProperty("LSL")
    public BigDecimal getLsl() {
        return lsl;
    }

    @JsonProperty("LSL")
    public void setLsl(BigDecimal lsl) {
        this.lsl = lsl;
    }

    @JsonProperty("LYD")
    public BigDecimal getLyd() {
        return lyd;
    }

    @JsonProperty("LYD")
    public void setLyd(BigDecimal lyd) {
        this.lyd = lyd;
    }

    @JsonProperty("MAD")
    public BigDecimal getMad() {
        return mad;
    }

    @JsonProperty("MAD")
    public void setMad(BigDecimal mad) {
        this.mad = mad;
    }

    @JsonProperty("MDL")
    public BigDecimal getMdl() {
        return mdl;
    }

    @JsonProperty("MDL")
    public void setMdl(BigDecimal mdl) {
        this.mdl = mdl;
    }

    @JsonProperty("MGA")
    public BigDecimal getMga() {
        return mga;
    }

    @JsonProperty("MGA")
    public void setMga(BigDecimal mga) {
        this.mga = mga;
    }

    @JsonProperty("MKD")
    public BigDecimal getMkd() {
        return mkd;
    }

    @JsonProperty("MKD")
    public void setMkd(BigDecimal mkd) {
        this.mkd = mkd;
    }

    @JsonProperty("MMK")
    public BigDecimal getMmk() {
        return mmk;
    }

    @JsonProperty("MMK")
    public void setMmk(BigDecimal mmk) {
        this.mmk = mmk;
    }

    @JsonProperty("MNT")
    public BigDecimal getMnt() {
        return mnt;
    }

    @JsonProperty("MNT")
    public void setMnt(BigDecimal mnt) {
        this.mnt = mnt;
    }

    @JsonProperty("MOP")
    public BigDecimal getMop() {
        return mop;
    }

    @JsonProperty("MOP")
    public void setMop(BigDecimal mop) {
        this.mop = mop;
    }

    @JsonProperty("MRU")
    public BigDecimal getMru() {
        return mru;
    }

    @JsonProperty("MRU")
    public void setMru(BigDecimal mru) {
        this.mru = mru;
    }

    @JsonProperty("MUR")
    public BigDecimal getMur() {
        return mur;
    }

    @JsonProperty("MUR")
    public void setMur(BigDecimal mur) {
        this.mur = mur;
    }

    @JsonProperty("MVR")
    public BigDecimal getMvr() {
        return mvr;
    }

    @JsonProperty("MVR")
    public void setMvr(BigDecimal mvr) {
        this.mvr = mvr;
    }

    @JsonProperty("MWK")
    public BigDecimal getMwk() {
        return mwk;
    }

    @JsonProperty("MWK")
    public void setMwk(BigDecimal mwk) {
        this.mwk = mwk;
    }

    @JsonProperty("MXN")
    public BigDecimal getMxn() {
        return mxn;
    }

    @JsonProperty("MXN")
    public void setMxn(BigDecimal mxn) {
        this.mxn = mxn;
    }

    @JsonProperty("MYR")
    public BigDecimal getMyr() {
        return myr;
    }

    @JsonProperty("MYR")
    public void setMyr(BigDecimal myr) {
        this.myr = myr;
    }

    @JsonProperty("MZN")
    public BigDecimal getMzn() {
        return mzn;
    }

    @JsonProperty("MZN")
    public void setMzn(BigDecimal mzn) {
        this.mzn = mzn;
    }

    @JsonProperty("NAD")
    public BigDecimal getNad() {
        return nad;
    }

    @JsonProperty("NAD")
    public void setNad(BigDecimal nad) {
        this.nad = nad;
    }

    @JsonProperty("NGN")
    public BigDecimal getNgn() {
        return ngn;
    }

    @JsonProperty("NGN")
    public void setNgn(BigDecimal ngn) {
        this.ngn = ngn;
    }

    @JsonProperty("NIO")
    public BigDecimal getNio() {
        return nio;
    }

    @JsonProperty("NIO")
    public void setNio(BigDecimal nio) {
        this.nio = nio;
    }

    @JsonProperty("NOK")
    public BigDecimal getNok() {
        return nok;
    }

    @JsonProperty("NOK")
    public void setNok(BigDecimal nok) {
        this.nok = nok;
    }

    @JsonProperty("NPR")
    public BigDecimal getNpr() {
        return npr;
    }

    @JsonProperty("NPR")
    public void setNpr(BigDecimal npr) {
        this.npr = npr;
    }

    @JsonProperty("NZD")
    public BigDecimal getNzd() {
        return nzd;
    }

    @JsonProperty("NZD")
    public void setNzd(BigDecimal nzd) {
        this.nzd = nzd;
    }

    @JsonProperty("OMR")
    public BigDecimal getOmr() {
        return omr;
    }

    @JsonProperty("OMR")
    public void setOmr(BigDecimal omr) {
        this.omr = omr;
    }

    @JsonProperty("PAB")
    public BigDecimal getPab() {
        return pab;
    }

    @JsonProperty("PAB")
    public void setPab(BigDecimal pab) {
        this.pab = pab;
    }

    @JsonProperty("PEN")
    public BigDecimal getPen() {
        return pen;
    }

    @JsonProperty("PEN")
    public void setPen(BigDecimal pen) {
        this.pen = pen;
    }

    @JsonProperty("PGK")
    public BigDecimal getPgk() {
        return pgk;
    }

    @JsonProperty("PGK")
    public void setPgk(BigDecimal pgk) {
        this.pgk = pgk;
    }

    @JsonProperty("PHP")
    public BigDecimal getPhp() {
        return php;
    }

    @JsonProperty("PHP")
    public void setPhp(BigDecimal php) {
        this.php = php;
    }

    @JsonProperty("PKR")
    public BigDecimal getPkr() {
        return pkr;
    }

    @JsonProperty("PKR")
    public void setPkr(BigDecimal pkr) {
        this.pkr = pkr;
    }

    @JsonProperty("PLN")
    public BigDecimal getPln() {
        return pln;
    }

    @JsonProperty("PLN")
    public void setPln(BigDecimal pln) {
        this.pln = pln;
    }

    @JsonProperty("PYG")
    public BigDecimal getPyg() {
        return pyg;
    }

    @JsonProperty("PYG")
    public void setPyg(BigDecimal pyg) {
        this.pyg = pyg;
    }

    @JsonProperty("QAR")
    public BigDecimal getQar() {
        return qar;
    }

    @JsonProperty("QAR")
    public void setQar(BigDecimal qar) {
        this.qar = qar;
    }

    @JsonProperty("RON")
    public BigDecimal getRon() {
        return ron;
    }

    @JsonProperty("RON")
    public void setRon(BigDecimal ron) {
        this.ron = ron;
    }

    @JsonProperty("RSD")
    public BigDecimal getRsd() {
        return rsd;
    }

    @JsonProperty("RSD")
    public void setRsd(BigDecimal rsd) {
        this.rsd = rsd;
    }

    @JsonProperty("RUB")
    public BigDecimal getRub() {
        return rub;
    }

    @JsonProperty("RUB")
    public void setRub(BigDecimal rub) {
        this.rub = rub;
    }

    @JsonProperty("RWF")
    public BigDecimal getRwf() {
        return rwf;
    }

    @JsonProperty("RWF")
    public void setRwf(BigDecimal rwf) {
        this.rwf = rwf;
    }

    @JsonProperty("SAR")
    public BigDecimal getSar() {
        return sar;
    }

    @JsonProperty("SAR")
    public void setSar(BigDecimal sar) {
        this.sar = sar;
    }

    @JsonProperty("SBD")
    public BigDecimal getSbd() {
        return sbd;
    }

    @JsonProperty("SBD")
    public void setSbd(BigDecimal sbd) {
        this.sbd = sbd;
    }

    @JsonProperty("SCR")
    public BigDecimal getScr() {
        return scr;
    }

    @JsonProperty("SCR")
    public void setScr(BigDecimal scr) {
        this.scr = scr;
    }

    @JsonProperty("SDG")
    public BigDecimal getSdg() {
        return sdg;
    }

    @JsonProperty("SDG")
    public void setSdg(BigDecimal sdg) {
        this.sdg = sdg;
    }

    @JsonProperty("SEK")
    public BigDecimal getSek() {
        return sek;
    }

    @JsonProperty("SEK")
    public void setSek(BigDecimal sek) {
        this.sek = sek;
    }

    @JsonProperty("SGD")
    public BigDecimal getSgd() {
        return sgd;
    }

    @JsonProperty("SGD")
    public void setSgd(BigDecimal sgd) {
        this.sgd = sgd;
    }

    @JsonProperty("SHP")
    public BigDecimal getShp() {
        return shp;
    }

    @JsonProperty("SHP")
    public void setShp(BigDecimal shp) {
        this.shp = shp;
    }

    @JsonProperty("SLL")
    public BigDecimal getSll() {
        return sll;
    }

    @JsonProperty("SLL")
    public void setSll(BigDecimal sll) {
        this.sll = sll;
    }

    @JsonProperty("SOS")
    public BigDecimal getSos() {
        return sos;
    }

    @JsonProperty("SOS")
    public void setSos(BigDecimal sos) {
        this.sos = sos;
    }

    @JsonProperty("SRD")
    public BigDecimal getSrd() {
        return srd;
    }

    @JsonProperty("SRD")
    public void setSrd(BigDecimal srd) {
        this.srd = srd;
    }

    @JsonProperty("SSP")
    public BigDecimal getSsp() {
        return ssp;
    }

    @JsonProperty("SSP")
    public void setSsp(BigDecimal ssp) {
        this.ssp = ssp;
    }

    @JsonProperty("STD")
    public BigDecimal getStd() {
        return std;
    }

    @JsonProperty("STD")
    public void setStd(BigDecimal std) {
        this.std = std;
    }

    @JsonProperty("STN")
    public BigDecimal getStn() {
        return stn;
    }

    @JsonProperty("STN")
    public void setStn(BigDecimal stn) {
        this.stn = stn;
    }

    @JsonProperty("SVC")
    public BigDecimal getSvc() {
        return svc;
    }

    @JsonProperty("SVC")
    public void setSvc(BigDecimal svc) {
        this.svc = svc;
    }

    @JsonProperty("SYP")
    public BigDecimal getSyp() {
        return syp;
    }

    @JsonProperty("SYP")
    public void setSyp(BigDecimal syp) {
        this.syp = syp;
    }

    @JsonProperty("SZL")
    public BigDecimal getSzl() {
        return szl;
    }

    @JsonProperty("SZL")
    public void setSzl(BigDecimal szl) {
        this.szl = szl;
    }

    @JsonProperty("THB")
    public BigDecimal getThb() {
        return thb;
    }

    @JsonProperty("THB")
    public void setThb(BigDecimal thb) {
        this.thb = thb;
    }

    @JsonProperty("TJS")
    public BigDecimal getTjs() {
        return tjs;
    }

    @JsonProperty("TJS")
    public void setTjs(BigDecimal tjs) {
        this.tjs = tjs;
    }

    @JsonProperty("TMT")
    public BigDecimal getTmt() {
        return tmt;
    }

    @JsonProperty("TMT")
    public void setTmt(BigDecimal tmt) {
        this.tmt = tmt;
    }

    @JsonProperty("TND")
    public BigDecimal getTnd() {
        return tnd;
    }

    @JsonProperty("TND")
    public void setTnd(BigDecimal tnd) {
        this.tnd = tnd;
    }

    @JsonProperty("TOP")
    public BigDecimal getTop() {
        return top;
    }

    @JsonProperty("TOP")
    public void setTop(BigDecimal top) {
        this.top = top;
    }

    @JsonProperty("TRY")
    public BigDecimal getTry() {
        return _try;
    }

    @JsonProperty("TRY")
    public void setTry(BigDecimal _try) {
        this._try = _try;
    }

    @JsonProperty("TTD")
    public BigDecimal getTtd() {
        return ttd;
    }

    @JsonProperty("TTD")
    public void setTtd(BigDecimal ttd) {
        this.ttd = ttd;
    }

    @JsonProperty("TWD")
    public BigDecimal getTwd() {
        return twd;
    }

    @JsonProperty("TWD")
    public void setTwd(BigDecimal twd) {
        this.twd = twd;
    }

    @JsonProperty("TZS")
    public BigDecimal getTzs() {
        return tzs;
    }

    @JsonProperty("TZS")
    public void setTzs(BigDecimal tzs) {
        this.tzs = tzs;
    }

    @JsonProperty("UAH")
    public BigDecimal getUah() {
        return uah;
    }

    @JsonProperty("UAH")
    public void setUah(BigDecimal uah) {
        this.uah = uah;
    }

    @JsonProperty("UGX")
    public BigDecimal getUgx() {
        return ugx;
    }

    @JsonProperty("UGX")
    public void setUgx(BigDecimal ugx) {
        this.ugx = ugx;
    }

    @JsonProperty("USD")
    public BigDecimal getUsd() {
        return usd;
    }

    @JsonProperty("USD")
    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    @JsonProperty("UYU")
    public BigDecimal getUyu() {
        return uyu;
    }

    @JsonProperty("UYU")
    public void setUyu(BigDecimal uyu) {
        this.uyu = uyu;
    }

    @JsonProperty("UZS")
    public BigDecimal getUzs() {
        return uzs;
    }

    @JsonProperty("UZS")
    public void setUzs(BigDecimal uzs) {
        this.uzs = uzs;
    }

    @JsonProperty("VES")
    public BigDecimal getVes() {
        return ves;
    }

    @JsonProperty("VES")
    public void setVes(BigDecimal ves) {
        this.ves = ves;
    }

    @JsonProperty("VND")
    public BigDecimal getVnd() {
        return vnd;
    }

    @JsonProperty("VND")
    public void setVnd(BigDecimal vnd) {
        this.vnd = vnd;
    }

    @JsonProperty("VUV")
    public BigDecimal getVuv() {
        return vuv;
    }

    @JsonProperty("VUV")
    public void setVuv(BigDecimal vuv) {
        this.vuv = vuv;
    }

    @JsonProperty("WST")
    public BigDecimal getWst() {
        return wst;
    }

    @JsonProperty("WST")
    public void setWst(BigDecimal wst) {
        this.wst = wst;
    }

    @JsonProperty("XAF")
    public BigDecimal getXaf() {
        return xaf;
    }

    @JsonProperty("XAF")
    public void setXaf(BigDecimal xaf) {
        this.xaf = xaf;
    }

    @JsonProperty("XAG")
    public BigDecimal getXag() {
        return xag;
    }

    @JsonProperty("XAG")
    public void setXag(BigDecimal xag) {
        this.xag = xag;
    }

    @JsonProperty("XAU")
    public BigDecimal getXau() {
        return xau;
    }

    @JsonProperty("XAU")
    public void setXau(BigDecimal xau) {
        this.xau = xau;
    }

    @JsonProperty("XCD")
    public BigDecimal getXcd() {
        return xcd;
    }

    @JsonProperty("XCD")
    public void setXcd(BigDecimal xcd) {
        this.xcd = xcd;
    }

    @JsonProperty("XDR")
    public BigDecimal getXdr() {
        return xdr;
    }

    @JsonProperty("XDR")
    public void setXdr(BigDecimal xdr) {
        this.xdr = xdr;
    }

    @JsonProperty("XOF")
    public BigDecimal getXof() {
        return xof;
    }

    @JsonProperty("XOF")
    public void setXof(BigDecimal xof) {
        this.xof = xof;
    }

    @JsonProperty("XPD")
    public BigDecimal getXpd() {
        return xpd;
    }

    @JsonProperty("XPD")
    public void setXpd(BigDecimal xpd) {
        this.xpd = xpd;
    }

    @JsonProperty("XPF")
    public BigDecimal getXpf() {
        return xpf;
    }

    @JsonProperty("XPF")
    public void setXpf(BigDecimal xpf) {
        this.xpf = xpf;
    }

    @JsonProperty("XPT")
    public BigDecimal getXpt() {
        return xpt;
    }

    @JsonProperty("XPT")
    public void setXpt(BigDecimal xpt) {
        this.xpt = xpt;
    }

    @JsonProperty("YER")
    public BigDecimal getYer() {
        return yer;
    }

    @JsonProperty("YER")
    public void setYer(BigDecimal yer) {
        this.yer = yer;
    }

    @JsonProperty("ZAR")
    public BigDecimal getZar() {
        return zar;
    }

    @JsonProperty("ZAR")
    public void setZar(BigDecimal zar) {
        this.zar = zar;
    }

    @JsonProperty("ZMW")
    public BigDecimal getZmw() {
        return zmw;
    }

    @JsonProperty("ZMW")
    public void setZmw(BigDecimal zmw) {
        this.zmw = zmw;
    }

    @JsonProperty("ZWL")
    public BigDecimal getZwl() {
        return zwl;
    }

    @JsonProperty("ZWL")
    public void setZwl(BigDecimal zwl) {
        this.zwl = zwl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
