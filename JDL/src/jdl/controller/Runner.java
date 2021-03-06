package jdl.controller;
import jdl.model.User;
import jdl.view.*;
public class Runner 
{
	private static User user = null;
	private static AccountCreate ac;
	private static AccountDelete ad;
	private static AccountManagement am;
	private static ActivityHistory aH;
	private static Login l;
	private static OptionList ol;
	private static Splash s;
	private static Tables t;
	private static TablesAddClient tac;
	private static TablesUpdateClient tuc;
	private static TablesRemarks tr;
	private static TablesStatus ts;
	private static TablesStatusPermits tsp;
	private static TablesUpdateTransactions tut;
	private static ViewAdministratorAccount vaa;
	private static GenerateClients gC;
	private static GenerateReport gR;
	private static GenerateExpiry gE;
	private static TablesDeleteClient tdc;
	private static optionsFAQ of;
	private static TablesDeleteTransaction tdt;
	private static EmailSending es;
	
    public static void main(String[] args) 
    {
        openSplash();
    }
    public static void openSplash()
    {
    	s = new Splash();
    	s.loadProgressBar();
    }
    public static void destroySplash()
    {
        s.setVisible(false);
        s.dispose();    
    }
    public static void openFAQ()
    {
    	of = new optionsFAQ();
    	of.setVisible(true);
    }
    
    public static void destroyFAQ()
    {
        of.setVisible(false);
        of.dispose();    
    }
    public static void openTDC()
    {   
        tdc = new TablesDeleteClient();
    }
    public static void destroyTDC()
    {
    	tdc.setVisible(false);
    	tdc.dispose();
    }
    public static void openTDT()
    {   
        tdt = new TablesDeleteTransaction();
    }
    public static void destroyTDT()
    {
    	tdt.setVisible(false);
    	tdt.dispose();
    }
    public static void openLogin()
    {   
        l = new Login();
    }
    public static void destroyLogin()
    {
    	l.setVisible(false);
    	l.dispose();
    }
    public static void openOptionList()
    {
    	ol = new OptionList();
    }
    public static void destroyOptionList()
    {
    	ol.setVisible(false);
    	ol.dispose();
    }
    public static void openViewAdminAcc()
    {
    	vaa = new ViewAdministratorAccount();
    }
    public static void destroyViewAdminAcc()
    {
    	vaa.setVisible(false);
    	vaa.dispose();
    }
    public static void openTables()
    {
    	t = new Tables();
    }
    public static void destroyTables()
    {
    	t.setVisible(false);
    	t.dispose();
    }
    public static void openAccountManagement()
    {
    	am = new AccountManagement();
    }
    public static void destroyAccountManagement()
    {
    	am.setVisible(false);
    	am.dispose();
    }
    public static void openTUT()
    {
    	tut = new TablesUpdateTransactions();
    }
    public static void destroyTUT()
    {
    	tut.setVisible(false);
    	tut.dispose();
    }
    public static void openAH()
    {
    	aH = new ActivityHistory();
    }
    public static void destroyAH()
    {
    	aH.setVisible(false);
    	aH.dispose();
    }
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Runner.user = user;
	}
    public static void openAC()
    {
    	ac = new AccountCreate();
    }
    public static void destroyAC()
    {
    	ac.dispose();
    }
    public static void openAD()
    {
    	ad = new AccountDelete();
    }
    public static void destroyAD()
    {
    	ad.setVisible(false);
    	ad.dispose();
    }
    public static void openTAC()
    {
    	tac = new TablesAddClient();
    }
    public static void destroyTAC()
    {
    	tac.setVisible(false);
    	tac.dispose();
    }
    public static void openTUC()
    {
    	tuc = new TablesUpdateClient();
    }
    public static void destroyTUC()
    {
    	tuc.setVisible(false);
    	tuc.dispose();
    }
    public static void openTR()
    {
    	tr = new TablesRemarks();
    }

    public static void destroyTR()
    {
    	tr.setVisible(false);
    	tr.dispose();
    }
    public static void openTS()
    {
    	ts = new TablesStatus();
    }
    public static void destroyTS()
    {
    	ts.setVisible(false);
    	ts.dispose();
    }
    public static void openTSP()
    {
    	tsp = new TablesStatusPermits();
    }
    public static void destroyTSP()
    {
    	tsp.setVisible(false);
    	tsp.dispose();
    }
    public static void openGR()
    {
    	gR = new GenerateReport();
    	gR.setVisible(true);
    }
    public static void destroyGR()
    {
    	gR.setVisible(false);
    	gR.dispose();
    }
    public static void openGC()
    {
    	gC = new GenerateClients();
    	gC.setVisible(true);
    }
    public static void destroyGC()
    {
    	gC.setVisible(false);
    	gC.dispose();
    }
    public static void openGE()
    {
    	gE = new GenerateExpiry();
    	gE.setVisible(true);
    }
    public static void destroyGE()
    {
    	gE.dispose();
    }
    public static void openES(){
        es = new EmailSending();
        es.loadProgressBar();
    }
	public static void destroyES() {
		es.dispose();
	}
}

