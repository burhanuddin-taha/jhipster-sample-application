import { element, by, ElementFinder } from 'protractor';

export class EntitiesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-entities div table .btn-danger'));
  title = element.all(by.css('jhi-entities div h2#page-heading span')).first();

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

export class EntitiesUpdatePage {
  pageTitle = element(by.id('jhi-entities-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  companyInput = element(by.id('field_company'));
  countryCodeInput = element(by.id('field_countryCode'));
  stateInput = element(by.id('field_state'));
  cityInput = element(by.id('field_city'));
  zipCodeInput = element(by.id('field_zipCode'));
  vatNumberInput = element(by.id('field_vatNumber'));
  cfInput = element(by.id('field_cf'));
  sdiInput = element(by.id('field_sdi'));
  ownedByInput = element(by.id('field_ownedBy'));
  geolocationInput = element(by.id('field_geolocation'));
  originUserIdInput = element(by.id('field_originUserId'));
  registrationInput = element(by.id('field_registration'));
  codeInput = element(by.id('field_code'));
  pecInput = element(by.id('field_pec'));
  numRivenditaInput = element(by.id('field_numRivendita'));
  websiteInput = element(by.id('field_website'));
  faxInput = element(by.id('field_fax'));
  profileInput = element(by.id('field_profile'));
  bankNameInput = element(by.id('field_bankName'));
  bankIbanInput = element(by.id('field_bankIban'));
  rankingInput = element(by.id('field_ranking'));
  businessInput = element(by.id('field_business'));
  originInput = element(by.id('field_origin'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCompanyInput(company) {
    await this.companyInput.sendKeys(company);
  }

  async getCompanyInput() {
    return await this.companyInput.getAttribute('value');
  }

  async setCountryCodeInput(countryCode) {
    await this.countryCodeInput.sendKeys(countryCode);
  }

  async getCountryCodeInput() {
    return await this.countryCodeInput.getAttribute('value');
  }

  async setStateInput(state) {
    await this.stateInput.sendKeys(state);
  }

  async getStateInput() {
    return await this.stateInput.getAttribute('value');
  }

  async setCityInput(city) {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput() {
    return await this.cityInput.getAttribute('value');
  }

  async setZipCodeInput(zipCode) {
    await this.zipCodeInput.sendKeys(zipCode);
  }

  async getZipCodeInput() {
    return await this.zipCodeInput.getAttribute('value');
  }

  async setVatNumberInput(vatNumber) {
    await this.vatNumberInput.sendKeys(vatNumber);
  }

  async getVatNumberInput() {
    return await this.vatNumberInput.getAttribute('value');
  }

  async setCfInput(cf) {
    await this.cfInput.sendKeys(cf);
  }

  async getCfInput() {
    return await this.cfInput.getAttribute('value');
  }

  async setSdiInput(sdi) {
    await this.sdiInput.sendKeys(sdi);
  }

  async getSdiInput() {
    return await this.sdiInput.getAttribute('value');
  }

  async setOwnedByInput(ownedBy) {
    await this.ownedByInput.sendKeys(ownedBy);
  }

  async getOwnedByInput() {
    return await this.ownedByInput.getAttribute('value');
  }

  async setGeolocationInput(geolocation) {
    await this.geolocationInput.sendKeys(geolocation);
  }

  async getGeolocationInput() {
    return await this.geolocationInput.getAttribute('value');
  }

  async setOriginUserIdInput(originUserId) {
    await this.originUserIdInput.sendKeys(originUserId);
  }

  async getOriginUserIdInput() {
    return await this.originUserIdInput.getAttribute('value');
  }

  async setRegistrationInput(registration) {
    await this.registrationInput.sendKeys(registration);
  }

  async getRegistrationInput() {
    return await this.registrationInput.getAttribute('value');
  }

  async setCodeInput(code) {
    await this.codeInput.sendKeys(code);
  }

  async getCodeInput() {
    return await this.codeInput.getAttribute('value');
  }

  async setPecInput(pec) {
    await this.pecInput.sendKeys(pec);
  }

  async getPecInput() {
    return await this.pecInput.getAttribute('value');
  }

  async setNumRivenditaInput(numRivendita) {
    await this.numRivenditaInput.sendKeys(numRivendita);
  }

  async getNumRivenditaInput() {
    return await this.numRivenditaInput.getAttribute('value');
  }

  async setWebsiteInput(website) {
    await this.websiteInput.sendKeys(website);
  }

  async getWebsiteInput() {
    return await this.websiteInput.getAttribute('value');
  }

  async setFaxInput(fax) {
    await this.faxInput.sendKeys(fax);
  }

  async getFaxInput() {
    return await this.faxInput.getAttribute('value');
  }

  async setProfileInput(profile) {
    await this.profileInput.sendKeys(profile);
  }

  async getProfileInput() {
    return await this.profileInput.getAttribute('value');
  }

  async setBankNameInput(bankName) {
    await this.bankNameInput.sendKeys(bankName);
  }

  async getBankNameInput() {
    return await this.bankNameInput.getAttribute('value');
  }

  async setBankIbanInput(bankIban) {
    await this.bankIbanInput.sendKeys(bankIban);
  }

  async getBankIbanInput() {
    return await this.bankIbanInput.getAttribute('value');
  }

  async setRankingInput(ranking) {
    await this.rankingInput.sendKeys(ranking);
  }

  async getRankingInput() {
    return await this.rankingInput.getAttribute('value');
  }

  async setBusinessInput(business) {
    await this.businessInput.sendKeys(business);
  }

  async getBusinessInput() {
    return await this.businessInput.getAttribute('value');
  }

  async setOriginInput(origin) {
    await this.originInput.sendKeys(origin);
  }

  async getOriginInput() {
    return await this.originInput.getAttribute('value');
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

export class EntitiesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-entities-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-entities'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
