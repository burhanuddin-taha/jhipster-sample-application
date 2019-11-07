// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { EntityStructureComponentsPage, EntityStructureDeleteDialog, EntityStructureUpdatePage } from './entity-structure.page-object';

const expect = chai.expect;

describe('EntityStructure e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entityStructureComponentsPage: EntityStructureComponentsPage;
  let entityStructureUpdatePage: EntityStructureUpdatePage;
  let entityStructureDeleteDialog: EntityStructureDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EntityStructures', async () => {
    await navBarPage.goToEntity('entity-structure');
    entityStructureComponentsPage = new EntityStructureComponentsPage();
    await browser.wait(ec.visibilityOf(entityStructureComponentsPage.title), 5000);
    expect(await entityStructureComponentsPage.getTitle()).to.eq('Entity Structures');
  });

  it('should load create EntityStructure page', async () => {
    await entityStructureComponentsPage.clickOnCreateButton();
    entityStructureUpdatePage = new EntityStructureUpdatePage();
    expect(await entityStructureUpdatePage.getPageTitle()).to.eq('Create or edit a Entity Structure');
    await entityStructureUpdatePage.cancel();
  });

  it('should create and save EntityStructures', async () => {
    const nbButtonsBeforeCreate = await entityStructureComponentsPage.countDeleteButtons();

    await entityStructureComponentsPage.clickOnCreateButton();
    await promise.all([
      entityStructureUpdatePage.setCategoryInput('category'),
      entityStructureUpdatePage.setCapacityInput('5'),
      entityStructureUpdatePage.entityIdSelectLastOption()
    ]);
    expect(await entityStructureUpdatePage.getCategoryInput()).to.eq('category', 'Expected Category value to be equals to category');
    expect(await entityStructureUpdatePage.getCapacityInput()).to.eq('5', 'Expected capacity value to be equals to 5');
    await entityStructureUpdatePage.save();
    expect(await entityStructureUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entityStructureComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last EntityStructure', async () => {
    const nbButtonsBeforeDelete = await entityStructureComponentsPage.countDeleteButtons();
    await entityStructureComponentsPage.clickOnLastDeleteButton();

    entityStructureDeleteDialog = new EntityStructureDeleteDialog();
    expect(await entityStructureDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Entity Structure?');
    await entityStructureDeleteDialog.clickOnConfirmButton();

    expect(await entityStructureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
