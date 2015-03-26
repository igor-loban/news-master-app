<!DOCTYPE html>
<html>
<head>
    <title>News Master</title>

    <link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css"/>

    <script type="text/javascript" src="js/external/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="js/external/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/external/lodash.min.js"></script>
    <script type="text/javascript" src="js/external/angular.js"></script>
    <script type="text/javascript" src="js/external/ui-bootstrap-tpls-0.12.1.min.js"></script>
    <script type="text/javascript" src="js/external/restangular.min.js"></script>

    <base href="${pageContext.request.contextPath}"/>
</head>
<body ng-app="app" ng-strict-di>
<div class="container-fluid" ng-controller="newsRecordController as controller">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3"
             style="padding: 5px; background: lightgrey; position: fixed; top: 0; z-index: 1000;">
            <div class="input-group">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" ng-click="controller.addNewsRecord()">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </span>
                <input type="text" class="form-control" placeholder="Search for..."
                       ng-model="controller.query.value" ng-keyup="$event.keyCode === 13 && controller.search()">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"
                            ng-click="controller.search()">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6 col-lg-offset-3" style="margin-top: 5px;">
            <div class="row" ng-repeat="newsRecord in controller.newsRecords">
                <hr/>
                <div class="col-lg-12">
                    <h4>{{newsRecord.title}}</h4>
                </div>
                <div class="col-lg-12" style="margin-bottom: 10px;">
                    {{newsRecord.text}}
                </div>
                <div class="col-lg-3">
                    Author: {{newsRecord.author}}
                </div>
                <div class="col-lg-8">
                    <span class="pull-right">{{newsRecord.publicationDate | date: "yyyy-MM-dd"}}</span>
                </div>
                <div class="col-lg-1">
                    <span class="glyphicon glyphicon-trash pull-right"
                          ng-click="controller.deleteNewsRecord(newsRecord)"
                          style="cursor: pointer;">
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/ng-template" id="add-news-record-dialog.html">
    <div class="modal-header">
        <h4 class="modal-title">Add News Record</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label>Title:</label>
            <input type="text" class="form-control" ng-model="controller.newsRecord.title"/>
        </div>
        <div class="form-group">
            <label>Author:</label>
            <input type="text" class="form-control" ng-model="controller.newsRecord.author"/>
        </div>
        <div class="form-group">
            <label>Text:</label>
            <textarea class="form-control" rows="5" ng-model="controller.newsRecord.text"></textarea>
        </div>
        <div class="form-group">
            <label>Publication date:</label>

            <div class="input-group">
                <input type="text" class="form-control" ng-model="controller.newsRecord.publicationDate"
                       datepicker-popup="yyyy-MM-dd" is-open="controller.calendarOpened"/>
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default" ng-click="controller.openCalendar($event)">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </button>
                </span>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-default" ng-click="controller.cancel()">Cancel</button>
        <button class="btn btn-primary" ng-click="controller.create()">Create</button>
    </div>
</script>

<script type="text/javascript" src="js/app/App.js"></script>
</body>
</html>
