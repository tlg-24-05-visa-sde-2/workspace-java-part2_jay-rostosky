package gov.irs;

public interface IRS {

    public static IRS getInstance() {
        return IRSEnum.INSTANCE;
    }

    public void collectTaxes();
    public void register(TaxPayer payer);
}
