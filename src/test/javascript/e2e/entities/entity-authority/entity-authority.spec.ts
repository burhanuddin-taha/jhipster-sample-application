// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { EntityAuthorityComponentsPage, EntityAuthorityDeleteDialog, EntityAuthorityUpdatePage } from './entity-authority.page-object';

const expect = chai.expect;

describe('EntityAuthority e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entityAuthorityComponentsPage: EntityAuthorityComponentsPage;
  let entityAuthorityUpdatePage: EntityAuthorityUpdatePage;
  let entityAuthorityDeleteDialog: EntityAuthorityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EntityAuthorities', async () => {
    await navBarPage.goToEntity('entity-authority');
    entityAuthorityComponentsPage = new EntityAuthorityComponentsPage();
    await browser.wait(ec.visibilityOf(entityAuthorityComponentsPage.title), 5000);
    expect(await entityAuthorityComponentsPage.getTitle()).to.eq('Entity Authorities');
  });

  it('should load create EntityAuthority page', async () => {
    await entityAuthorityComponentsPage.clickOnCreateButton();
    entityAuthorityUpdatePage = new EntityAuthorityUpdatePage();
    expect(await entityAuthorityUpdatePage.getPageTitle()).to.eq('Create or edit a Entity Authority');
    await entityAuthorityUpdatePage.cancel();
  });

  it('should create and save EntityAuthorities', async () => {
    const nbButtonsBeforeCreate = await entityAuthorityComponentsPage.countDeleteButtons();

    await entityAuthorityComponentsPage.clickOnCreateButton();
    await promise.all([
      entityAuthorityUpdatePage.setUserIdInput('5'),
      entityAuthorityUpdatePage.setEntityIdInput('5'),
      entityAuthorityUpdatePage.setEntityNameInput('entityName')
    ]);
    expect(await entityAuthorityUpdatePage.getUserIdInput()).to.eq('5', 'Expected userId value to be equals to 5');
    expect(await entityAuthorityUpdatePage.getEntityIdInput()).to.eq('5', 'Expected entityId value to be equals to 5');
    expect(await entityAuthorityUpdatePage.getEntityNameInput()).to.eq(
      'entityName',
      'Expected EntityName value to be equals to entityName'
    );
    await entityAuthorityUpdatePage.save();
    expect(await entityAuthorityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entityAuthorityComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last EntityAuthority', async () => {
    const nbButtonsBeforeDelete = await entityAuthorityComponentsPage.countDeleteButtons();
    await entityAuthorityComponentsPage.clickOnLastDeleteButton();

    entityAuthorityDeleteDialog = new EntityAuthorityDeleteDialog();
    expect(await entityAuthorityDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Entity Authority?');
    await entityAuthorityDeleteDialog.clickOnConfirmButton();

    expect(await entityAuthorityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
