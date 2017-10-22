package com.spiegel.stock.pojo;


public class Symbol {
    private String symbol_name;
    private String company_name;
    private String sector;
    private String industry;

    public Symbol(String symbol_name, String company_name, String sector, String industry){
        this.symbol_name = symbol_name;
        this.company_name = company_name;
        this.sector = sector;
        this.industry = industry;
    }

    public String getSymbol_name() {
        return symbol_name;
    }

    public Symbol setSymbol_name(String symbol_name) {
        this.symbol_name = symbol_name;
        return this;
    }

    public String getCompany_name() {
        return company_name;
    }

    public Symbol setCompany_name(String company_name) {
        this.company_name = company_name;
        return this;
    }

    public String getSector() {
        return sector;
    }

    public Symbol setSector(String sector) {
        this.sector = sector;
        return this;
    }

    public String getIndustry() {
        return industry;
    }

    public Symbol setIndustry(String industry) {
        this.industry = industry;
        return this;
    }
}
