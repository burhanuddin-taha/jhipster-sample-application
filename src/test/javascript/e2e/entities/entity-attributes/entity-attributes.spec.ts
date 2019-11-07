// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { EntityAttributesComponentsPage, EntityAttributesDeleteDialog, EntityAttributesUpdatePage } from './entity-attributes.page-object';

const expect = chai.expect;

describe('EntityAttributes e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entityAttributesComponentsPage: EntityAttributesComponentsPage;
  let entityAttributesUpdatePage: EntityAttributesUpdatePage;
  let entityAttributesDeleteDialog: EntityAttributesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EntityAttributes', async () => {
    await navBarPage.goToEntity('entity-attributes');
    entityAttributesComponentsPage = new EntityAttributesComponentsPage();
    await browser.wait(ec.visibilityOf(entityAttributesComponentsPage.title), 5000);
    expect(await entityAttributesComponentsPage.getTitle()).to.eq('Entity Attributes');
  });

  it('should load create EntityAttributes page', async () => {
    await entityAttributesComponentsPage.clickOnCreateButton();
    entityAttributesUpdatePage = new EntityAttributesUpdatePage();
    expect(await entityAttributesUpdatePage.getPageTitle()).to.eq('Create or edit a Entity Attributes');
    await entityAttributesUpdatePage.cancel();
  });

  it('should create and save EntityAttributes', async () => {
    const nbButtonsBeforeCreate = await entityAttributesComponentsPage.countDeleteButtons();

    await entityAttributesComponentsPage.clickOnCreateButton();
    await promise.all([
      entityAttributesUpdatePage.setCategotyNameInput('categotyName'),
      entityAttributesUpdatePage.entitiesSelectLastOption()
    ]);
    expect(await entityAttributesUpdatePage.getCategotyNameInput()).to.eq(
      'categotyName',
      'Expected CategotyName value to be equals to categotyName'
    );
    await entityAttributesUpdatePage.save();
    expect(await entityAttributesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entityAttributesComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last EntityAttributes', async () => {
    const nbButtonsBeforeDelete = await entityAttributesComponentsPage.countDeleteButtons();
    await entityAttributesComponentsPage.clickOnLastDeleteButton();

    entityAttributesDeleteDialog = new EntityAttributesDeleteDialog();
    expect(await entityAttributesDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Entity Attributes?');
    await entityAttributesDeleteDialog.clickOnConfirmButton();

    expect(await entityAttributesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
