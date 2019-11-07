// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { EntitiesComponentsPage, EntitiesDeleteDialog, EntitiesUpdatePage } from './entities.page-object';

const expect = chai.expect;

describe('Entities e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entitiesComponentsPage: EntitiesComponentsPage;
  let entitiesUpdatePage: EntitiesUpdatePage;
  let entitiesDeleteDialog: EntitiesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Entities', async () => {
    await navBarPage.goToEntity('entities');
    entitiesComponentsPage = new EntitiesComponentsPage();
    await browser.wait(ec.visibilityOf(entitiesComponentsPage.title), 5000);
    expect(await entitiesComponentsPage.getTitle()).to.eq('Entities');
  });

  it('should load create Entities page', async () => {
    await entitiesComponentsPage.clickOnCreateButton();
    entitiesUpdatePage = new EntitiesUpdatePage();
    expect(await entitiesUpdatePage.getPageTitle()).to.eq('Create or edit a Entities');
    await entitiesUpdatePage.cancel();
  });

  it('should create and save Entities', async () => {
    const nbButtonsBeforeCreate = await entitiesComponentsPage.countDeleteButtons();

    await entitiesComponentsPage.clickOnCreateButton();
    await promise.all([
      entitiesUpdatePage.setCompanyInput('company'),
      entitiesUpdatePage.setCountryCodeInput('countryCode'),
      entitiesUpdatePage.setStateInput('state'),
      entitiesUpdatePage.setCityInput('city'),
      entitiesUpdatePage.setZipCodeInput('zipCode'),
      entitiesUpdatePage.setVatNumberInput('vatNumber'),
      entitiesUpdatePage.setCfInput('cf'),
      entitiesUpdatePage.setSdiInput('sdi'),
      entitiesUpdatePage.setOwnedByInput('5'),
      entitiesUpdatePage.setGeolocationInput('geolocation'),
      entitiesUpdatePage.setOriginUserIdInput('5'),
      entitiesUpdatePage.setRegistrationInput('registration'),
      entitiesUpdatePage.setCodeInput('code'),
      entitiesUpdatePage.setPecInput('pec'),
      entitiesUpdatePage.setNumRivenditaInput('numRivendita'),
      entitiesUpdatePage.setWebsiteInput('website'),
      entitiesUpdatePage.setFaxInput('fax'),
      entitiesUpdatePage.setProfileInput('profile'),
      entitiesUpdatePage.setBankNameInput('bankName'),
      entitiesUpdatePage.setBankIbanInput('bankIban'),
      entitiesUpdatePage.setRankingInput('5'),
      entitiesUpdatePage.setBusinessStringInput('businessString'),
      entitiesUpdatePage.setOriginInput('origin')
    ]);
    expect(await entitiesUpdatePage.getCompanyInput()).to.eq('company', 'Expected Company value to be equals to company');
    expect(await entitiesUpdatePage.getCountryCodeInput()).to.eq('countryCode', 'Expected CountryCode value to be equals to countryCode');
    expect(await entitiesUpdatePage.getStateInput()).to.eq('state', 'Expected State value to be equals to state');
    expect(await entitiesUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await entitiesUpdatePage.getZipCodeInput()).to.eq('zipCode', 'Expected ZipCode value to be equals to zipCode');
    expect(await entitiesUpdatePage.getVatNumberInput()).to.eq('vatNumber', 'Expected VatNumber value to be equals to vatNumber');
    expect(await entitiesUpdatePage.getCfInput()).to.eq('cf', 'Expected Cf value to be equals to cf');
    expect(await entitiesUpdatePage.getSdiInput()).to.eq('sdi', 'Expected Sdi value to be equals to sdi');
    expect(await entitiesUpdatePage.getOwnedByInput()).to.eq('5', 'Expected ownedBy value to be equals to 5');
    expect(await entitiesUpdatePage.getGeolocationInput()).to.eq('geolocation', 'Expected Geolocation value to be equals to geolocation');
    expect(await entitiesUpdatePage.getOriginUserIdInput()).to.eq('5', 'Expected originUserId value to be equals to 5');
    expect(await entitiesUpdatePage.getRegistrationInput()).to.eq(
      'registration',
      'Expected Registration value to be equals to registration'
    );
    expect(await entitiesUpdatePage.getCodeInput()).to.eq('code', 'Expected Code value to be equals to code');
    expect(await entitiesUpdatePage.getPecInput()).to.eq('pec', 'Expected Pec value to be equals to pec');
    expect(await entitiesUpdatePage.getNumRivenditaInput()).to.eq(
      'numRivendita',
      'Expected NumRivendita value to be equals to numRivendita'
    );
    expect(await entitiesUpdatePage.getWebsiteInput()).to.eq('website', 'Expected Website value to be equals to website');
    expect(await entitiesUpdatePage.getFaxInput()).to.eq('fax', 'Expected Fax value to be equals to fax');
    expect(await entitiesUpdatePage.getProfileInput()).to.eq('profile', 'Expected Profile value to be equals to profile');
    expect(await entitiesUpdatePage.getBankNameInput()).to.eq('bankName', 'Expected BankName value to be equals to bankName');
    expect(await entitiesUpdatePage.getBankIbanInput()).to.eq('bankIban', 'Expected BankIban value to be equals to bankIban');
    expect(await entitiesUpdatePage.getRankingInput()).to.eq('5', 'Expected ranking value to be equals to 5');
    expect(await entitiesUpdatePage.getBusinessStringInput()).to.eq(
      'businessString',
      'Expected BusinessString value to be equals to businessString'
    );
    expect(await entitiesUpdatePage.getOriginInput()).to.eq('origin', 'Expected Origin value to be equals to origin');
    await entitiesUpdatePage.save();
    expect(await entitiesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entitiesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Entities', async () => {
    const nbButtonsBeforeDelete = await entitiesComponentsPage.countDeleteButtons();
    await entitiesComponentsPage.clickOnLastDeleteButton();

    entitiesDeleteDialog = new EntitiesDeleteDialog();
    expect(await entitiesDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Entities?');
    await entitiesDeleteDialog.clickOnConfirmButton();

    expect(await entitiesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
