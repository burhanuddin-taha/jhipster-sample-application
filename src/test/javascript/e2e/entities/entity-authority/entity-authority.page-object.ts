import { element, by, ElementFinder } from 'protractor';

export class EntityAuthorityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-entity-authority div table .btn-danger'));
  title = element.all(by.css('jhi-entity-authority div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getText();
  }
}

export class EntityAuthorityUpdatePage {
  pageTitle = element(by.id('jhi-entity-authority-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  userIdInput = element(by.id('field_userId'));
  entityIdInput = element(by.id('field_entityId'));
  entityNameInput = element(by.id('field_entityName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setUserIdInput(userId) {
    await this.userIdInput.sendKeys(userId);
  }

  async getUserIdInput() {
    return await this.userIdInput.getAttribute('value');
  }

  async setEntityIdInput(entityId) {
    await this.entityIdInput.sendKeys(entityId);
  }

  async getEntityIdInput() {
    return await this.entityIdInput.getAttribute('value');
  }

  async setEntityNameInput(entityName) {
    await this.entityNameInput.sendKeys(entityName);
  }

  async getEntityNameInput() {
    return await this.entityNameInput.getAttribute('value');
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class EntityAuthorityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-entityAuthority-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-entityAuthority'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
