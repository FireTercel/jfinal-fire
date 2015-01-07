define(['app', 'bootstrap-multiselect', 'member.service'], function() {
  'use strict';
  return $(function() {
    App.Controller.MemberController = {
      init: function() {
        switch (App.Service.ConfigSrv.path("/member")) {
          case '/':
          case '/branch':
            App.Service.MemberSrv.branch();
            App.Service.MemberSrv["delete"]();
            App.Service.MemberSrv.control('save', '#saveForm', '#saveModal', '#saveModal button.ok');
            App.Service.MemberSrv.control('update', '#updateForm', '#updateModal', '#updateModal button.ok');
            return App.Service.ConfigSrv.multiselect('[name="region_id"]', '[name="branch_id"]');
        }
      }
    };
    return App.Controller.MemberController.init();
  });
});
