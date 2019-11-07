import { element, by, ElementFinder } from 'protractor';

export class BusinessTypeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-business-type div table .btn-danger'));
  title = element.all(by.css('jhi-business-type div h2#page-heading span')).first();

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

export class BusinessTypeUpdatePage {
  pageTitle = element(by.id('jhi-business-type-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  typeInput = element(by.id('field_type'));
  entitiesSelect = element(by.id('field_entities'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTypeInput(type) {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput() {
    return await this.typeInput.getAttribute('value');
  }

  async entitiesSelectLastOption(timeout?: number) {
    await this.entitiesSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async entitiesSelectOption(option) {
    await this.entitiesSelect.sendKeys(option);
  }

  getEntitiesSelect(): ElementFinder {
    return this.entitiesSelect;
  }

  async getEntitiesSelectedOption() {
    return await this.entitiesSelect.element(by.css('option:checked')).getText();
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

export class BusinessTypeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-businessType-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-businessType'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
