package com.example.domain;


public enum EventType {

  WAYF_DISPLAYED("event.wayf.displayed"),
  USER_ALREADY_BOARDED("event.user.already.boarded"),
  USER_BOARDING("event.user.boarding"),
  USER_BOARDING_SUCCESFUL("event.user.boarding.successful"),
  USER_BOARDING_FAILED("event.user.boarding.failed"),
  USER_BOARDING_DUPLICATE("event.user.boarding.duplicate"),
  USER_COMMUNICATION_PREFERENCES_UPDATED("event.user.communication.preferences.updated"),
  USER_ACCESSED_PROFILE("event.user.accessed.profile"),
  USER_CHANGED_EMAIL("event.user.change.email.success"),
  USER_CHANGE_EMAIL_FAILED("event.user.change.email.failed"),
  USER_CHANGED_ACCOUNT_NAME("event.user.change.account.name.success"),
  USER_CHANGE_ACCOUNT_NAME_FAILED("event.user.change.account.name.failed"),
  USER_CHANGED_PASSWORD("event.user.change.password.success"),
  USER_CHANGE_PASSWORD_FAILED("event.user.change.password.failed"),
  USER_OPT_IN_DUPLICATE("event.user.opt.in.duplicate"),
  USER_OPTED_IN("event.user.opt.in.success"),
  USER_OPTED_OUT("event.user.opted.out"),
  USER_DELETE_SUCCESSFUL("event.user.delete.successful"),
  USER_DELETE_FAILED("event.user.delete.failed"),
  IDENTITY_DELETE_SUCCESSFUL("event.user.identity.delete.successful"),
  LOGIN_INTERCEPTOR_CREATE_SESSION("event.login.interceptor.create.session"),
  LOGIN_INTERCEPTOR_REDIRECT_TO_WAYF("event.login.interceptor.redirect.to.wayf"),
  LOGIN_INTERCEPTOR_SOCIAL_IDENTITY_NOT_FOUND("event.login.interceptor.social.identity.not.found"),
  LOGIN_FAILED("event.login.failed"),
  LOGOUT("event.logout"),
  RESOURCE_ACCESSED("event.resource.accessed"),
  RESOURCE_ACCESS_FAILED("event.resource.access.failed"),
  USER_BLOCKED("event.user.blocked"),
  TAM_USER_UNBLOCK_FAILED("event.user.unblock.failed"),
  CONTACT_INFORMATION_UPDATE_FAILED("event.contact.information.update.failed"),
  CONTACT_INFORMATION_UPDATE_SUCCESSFUL("event.contact.information.update.successful"),
  ACTIVATION_UPDATE_FAILED("event.activation.failed"),
  ACTIVATION_UPDATE_SUCCESSFUL("event.activation.successful"),
  ACTIVATION_MAX_NUMBER_OF_TRIES_EXCEEDED("event.activation.max.number.of.tries.exceeded"),
  ACTIVATION_CODE_NOT_FOUND("event.activation.code.not.found"),
  ACTIVATION_CODE_EXPIRED("event.activation.code.expired"),
  PASSWORD_MAX_RETRIES_EXCEEDED("event.password.max.retries.reached"),
  PIN_ATTEMPT_FAILED("event.pin.attempt.failed"),
  PIN_ATTEMPT_SUCCESSFUL("event.pin.attempt.successful"),
  PIN_MAX_RETRIES_EXCEEDED("event.pin.max.retries.exceeded"),
  PIN_VALID_CHECK("event.pin.valid.check"),
  PIN_CHANGE_SUCCESSFUL("event.pin.change.successful"),
  PIN_CHANGE_FAILED("event.pin.change.failed"),
  PIN_FORGOTTEN("event.pin.forgotten"),
  ACTIVATION_CODE_FORGOTTEN("event.activation.code.forgotten"),
  ADMIN_USER_DELETED("event.admin.user.deleted"),
  ADMIN_USER_BLOCKED("event.admin.user.blocked"),
  ADMIN_USER_UNBLOCKED("event.admin.user.unblocked"),
  SETTING_BOARDING_ENABLED("event.setting.boarding.enabled"),
  SETTING_BOARDING_DISABLED("event.setting.boarding.disabled"),
  SETTING_ACTIVATION_ENABLED("event.setting.activation.enabled"),
  SETTING_ACTIVATION_DISABLED("event.setting.activation.disabled"),
  SETTING_LOGIN_ENABLED("event.setting.login.enabled"),
  SETTING_LOGIN_DISABLED("event.setting.login.disabled"),
  OAUTH_EVENT("event.oauth.event"),

  ALERT_INVALID_GRANT("event.oauth.invalid.grant"),
  ALERT_INVALID_CLIENT("event.oauth.invalid.client"),

  CCC_USER_DETAILS_ACCESSED("event.ccc.user.details.accessed"),
  CCC_USER_BLOCKED("event.ccc.user.blocked"),
  CCC_USER_UNBLOCKED("event.ccc.user.unblocked"),
  CCC_ACCOUNT_BLOCKED("event.ccc.account.blocked"),
  CCC_ACCOUNT_UNBLOCKED("event.ccc.account.unblocked"),
  CCC_ACCOUNT_REMOVED("event.ccc.account.removed"),
  CCC_DEVICE_SESSIONS_TERMINATED("event.ccc.device.sessions.terminated"),

  INVALID_APP_HEADER("event.mobile.invalid.app.header"),

  OPENTOKEN_EVENT("event.opentoken.event"),

  DIRECT_REGISTRATION_CREATE_SUCCESS("event.direct.registration.create.success"),
  DIRECT_REGISTRATION_CREATE_FAILED("event.direct.registration.create.failed"),
  DIRECT_REGISTRATION_STARTED("event.direct.registration.started"),
  DIRECT_REGISTRATION_EXPIRED("event.direct.registration.expired"),
  DIRECT_REGISTRATION_MAX_RETRIES_EXCEEDED("event.direct.registration.max.retries.exceeded"),
  DIRECT_REGISTRATION_EXCEPTION("event.direct.registration.exception");

  private final String description;

  EventType(final String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
