// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { BusinessTypeComponentsPage, BusinessTypeDeleteDialog, BusinessTypeUpdatePage } from './business-type.page-object';

const expect = chai.expect;

describe('BusinessType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let businessTypeComponentsPage: BusinessTypeComponentsPage;
  let businessTypeUpdatePage: BusinessTypeUpdatePage;
  let businessTypeDeleteDialog: BusinessTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load BusinessTypes', async () => {
    await navBarPage.goToEntity('business-type');
    businessTypeComponentsPage = new BusinessTypeComponentsPage();
    await browser.wait(ec.visibilityOf(businessTypeComponentsPage.title), 5000);
    expect(await businessTypeComponentsPage.getTitle()).to.eq('Business Types');
  });

  it('should load create BusinessType page', async () => {
    await businessTypeComponentsPage.clickOnCreateButton();
    businessTypeUpdatePage = new BusinessTypeUpdatePage();
    expect(await businessTypeUpdatePage.getPageTitle()).to.eq('Create or edit a Business Type');
    await businessTypeUpdatePage.cancel();
  });

  it('should create and save BusinessTypes', async () => {
    const nbButtonsBeforeCreate = await businessTypeComponentsPage.countDeleteButtons();

    await businessTypeComponentsPage.clickOnCreateButton();
    await promise.all([businessTypeUpdatePage.setTypeInput('type'), businessTypeUpdatePage.entitiesSelectLastOption()]);
    expect(await businessTypeUpdatePage.getTypeInput()).to.eq('type', 'Expected Type value to be equals to type');
    await businessTypeUpdatePage.save();
    expect(await businessTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await businessTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last BusinessType', async () => {
    const nbButtonsBeforeDelete = await businessTypeComponentsPage.countDeleteButtons();
    await businessTypeComponentsPage.clickOnLastDeleteButton();

    businessTypeDeleteDialog = new BusinessTypeDeleteDialog();
    expect(await businessTypeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Business Type?');
    await businessTypeDeleteDialog.clickOnConfirmButton();

    expect(await businessTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
