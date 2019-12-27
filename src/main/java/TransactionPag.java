public class TransactionPag implements Commons {
    public String getTitle() {
        System.out.println("class name is "+TransactionPag.class.getName());
        return null;
    }

    public void clickButton() {

    }

    public int getItemCount() {
        return 0;
    }

    public boolean isDisplayed() {
        return false;
    }

    public boolean isValid() {
        System.out.println("hmm");
        return false;
    }
}
