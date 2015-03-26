(function () {
    "use strict";

    angular.module("app", [
        "ui.bootstrap",
        "restangular",
        "nwNewsMaster"
    ]).constant("Urls", {
        API_PREFIX: "api/v1"
    }).run(initRestangular);

    angular.module("nwNewsMaster", [])
        .service("newsRecordRepository", NewsRecordRepository)
        .controller("newsRecordController", NewsRecordController)
        .controller("addNewsRecordDialogController", AddNewsRecordDialogController);

    initRestangular.$inject = ["Restangular", "Urls"];
    function initRestangular(Restangular, Urls) {
        Restangular.setBaseUrl(Urls.API_PREFIX);
    }

    NewsRecordRepository.$inject = ["Restangular"];
    function NewsRecordRepository(Restangular) {
        var self = this,
            controllerPath = "news-records";

        self.create = function (newsRecord) {
            return Restangular.all(controllerPath).customPOST(newsRecord, "create");
        };

        self.search = function (query) {
            return Restangular.all(controllerPath).customPOST(query, "search");
        };

        self.delete = function (id) {
            return Restangular.all(controllerPath).customPOST(id, "delete");
        };
    }

    NewsRecordController.$inject = ["$log", "$modal", "newsRecordRepository"];
    function NewsRecordController($log, $modal, newsRecordRepository) {
        var self = this;

        self.query = {
            value: ""
        };
        self.newsRecords = [];

        self.addNewsRecord = function () {
            $modal.open({
                templateUrl: "add-news-record-dialog.html",
                controller: "addNewsRecordDialogController as controller"
            }).result.then(function (newsRecord) {
                    $log.debug("newsRecordController: addNewsRecord: newsRecord = ", newsRecord);
                    newsRecordRepository.create(newsRecord).then(function (response) {
                        $log.debug("newsRecordController: newsRecordRepository: create: response = ", response);
                        fetchNewsRecord();
                    }, function (response) {
                        $log.debug("newsRecordController: newsRecordRepository: create: failed response = ", response);
                    });
                });
        };

        self.search = function () {
            $log.debug("newsRecordController: search");
            fetchNewsRecord();
        };

        self.deleteNewsRecord = function (newsRecord) {
            $log.debug("newsRecordController: deleteNewRecord");
            newsRecordRepository.delete(newsRecord.id).then(function (response) {
                $log.debug("newsRecordController: newsRecordRepository: delete: response = ", response);
                fetchNewsRecord();
            }, function (response) {
                $log.debug("newsRecordController: newsRecordRepository: delete: failed response = ", response);
            });
        };

        fetchNewsRecord();

        function fetchNewsRecord() {
            $log.debug("newsRecordController: fetchNewsRecord");
            newsRecordRepository.search(self.query).then(function (newsRecords) {
                $log.debug("newsRecordController: newsRecordRepository: search: newsRecords = ", newsRecords);
                self.newsRecords = newsRecords;
            }, function (response) {
                $log.debug("newsRecordController: newsRecordRepository: search: failed response = ", response);
            });
        }
    }

    AddNewsRecordDialogController.$inject = ["$log", "$modalInstance"];
    function AddNewsRecordDialogController($log, $modalInstance) {
        var self = this;

        self.newsRecord = {
            title: "",
            author: "",
            text: "",
            publicationDate: null
        };

        self.calendarOpened = false;

        self.openCalendar = function ($event) {
            $log.debug("addNewsRecordDialogController: openCalendar");
            $event.preventDefault();
            $event.stopPropagation();
            self.calendarOpened = true;
        };

        self.create = function () {
            $log.debug("addNewsRecordDialogController: create: self.newsRecord = ", self.newsRecord);
            if (self.newsRecord.publicationDate) {
                self.newsRecord.publicationDate = +self.newsRecord.publicationDate;
            }
            $modalInstance.close(self.newsRecord);
        };

        self.cancel = function () {
            $log.debug("addNewsRecordDialogController: cancel");
            $modalInstance.dismiss("cancel");
        };
    }
})();