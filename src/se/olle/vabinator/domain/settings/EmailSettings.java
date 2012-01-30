package se.olle.vabinator.domain.settings;


public class EmailSettings {
    public static final EmailSettings DEFAULT_EMAIL_SETTINGS = new EmailSettings("", "Jag är hemma med sjukt barn", "Jag är tillbaka på kontoret");

    public EmailSettings(String mailTo, String reportVabText, String reportBackToWorkText) {
        this.mailTo = mailTo;
        this.reportVabText = reportVabText;
        this.reportBackToWorkText = reportBackToWorkText;
    }

    public EmailSettings() {

    }

    private String mailTo;
    private String reportVabText;
    private String reportBackToWorkText;

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getReportVabText() {
        return reportVabText;
    }

    public void setReportVabText(String reportVabText) {
        this.reportVabText = reportVabText;
    }

    @Override
    public String toString() {
        return "EmailSettings{" +
                "mailTo='" + mailTo + '\'' +
                ", reportVabText='" + reportVabText + '\'' +
                '}';
    }

    public String getReportBackToWorkText() {
        return reportBackToWorkText;
    }

    public void setReportBackToWorkText(String reportBackToWorkText) {
        this.reportBackToWorkText = reportBackToWorkText;
    }
}
