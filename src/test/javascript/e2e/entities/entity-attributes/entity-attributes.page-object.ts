import { element, by, ElementFinder } from 'protractor';

export class EntityAttributesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-entity-attributes div table .btn-danger'));
  title = element.all(by.css('jhi-entity-attributes div h2#page-heading span')).first();

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

export class EntityAttributesUpdatePage {
  pageTitle = element(by.id('jhi-entity-attributes-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  categotyNameInput = element(by.id('field_categotyName'));
  entityIdSelect = element(by.id('field_entityId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCategotyNameInput(categotyName) {
    await this.categotyNameInput.sendKeys(categotyName);
  }

  async getCategotyNameInput() {
    return await this.categotyNameInput.getAttribute('value');
  }

  async entityIdSelectLastOption(timeout?: number) {
    await this.entityIdSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async entityIdSelectOption(option) {
    await this.entityIdSelect.sendKeys(option);
  }

  getEntityIdSelect(): ElementFinder {
    return this.entityIdSelect;
  }

  async getEntityIdSelectedOption() {
    return await this.entityIdSelect.element(by.css('option:checked')).getText();
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

export class EntityAttributesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-entityAttributes-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-entityAttributes'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
