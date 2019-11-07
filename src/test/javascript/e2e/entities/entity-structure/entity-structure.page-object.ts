import { element, by, ElementFinder } from 'protractor';

export class EntityStructureComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-entity-structure div table .btn-danger'));
  title = element.all(by.css('jhi-entity-structure div h2#page-heading span')).first();

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

export class EntityStructureUpdatePage {
  pageTitle = element(by.id('jhi-entity-structure-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  categoryInput = element(by.id('field_category'));
  capacityInput = element(by.id('field_capacity'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCategoryInput(category) {
    await this.categoryInput.sendKeys(category);
  }

  async getCategoryInput() {
    return await this.categoryInput.getAttribute('value');
  }

  async setCapacityInput(capacity) {
    await this.capacityInput.sendKeys(capacity);
  }

  async getCapacityInput() {
    return await this.capacityInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class EntityStructureDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-entityStructure-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-entityStructure'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
