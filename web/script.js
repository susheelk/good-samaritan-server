var loggedIn = false;
const onPageLoad = {
    "#dash": function () {
        viewModel.getPosts();
    }
};

function setHash(hash) {
    if (hash !== getHash()) {
        location.hash = hash;
    }
    $(window).trigger("hashchange");
}

ActivityViewModel = function () {
    this.userName = ko.observable();
    this.userEmail = ko.observable();
    this.userId = ko.observable();
    this.userPoints = ko.observable();

    this.posts=ko.observableArray();

    this.login = function (form) {
        console.log(form.email.value);
        $.get('/login', {
            'email': form.email.value,
            'password': form.password.value
        }, function (resp) {
            if (resp["error"] === null) {
                loggedIn = true;
                location.hash = "#dash";
                var data = resp.data;
                viewModel.userEmail(data.email);
                viewModel.userName(data.name);
                viewModel.userPoints(data.points);
                viewModel.userId(data.userId)
            }
        });
    }

    this.getPosts = function () {
        $.get('/listneeded', {}, function (resp) {
            viewModel.posts(resp.data);
        });
    }
};
var viewModel = new ActivityViewModel();
$(window).on('hashchange', function () {
    const body = $("body");
    console.log(location.hash);
    $('div.page').hide();
    $(location.hash+'.page').show();
    if(!loggedIn && location.hash !== "#login") {
        location.hash = "#login";
        $(window).trigger("hashchage");
        $('#nav').hide();
    }
    if(loggedIn) {
        $('#nav').show();
    }

    if(!loggedIn) {
        $('#nav').hide();
    }

    onPageLoad[location.hash]();
});

$(document).ready(function () {
    location.hash = "#login";
    $('nav').hide();
    ko.applyBindings(viewModel);
});

