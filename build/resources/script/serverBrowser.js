$(document).ready( function() {
    $('#browser_container').fileTree({
        root: '/home',
        script: 'resources/script/jqueryFileTree/jqueryFileTree.jsp',
        expandSpeed: 1000,
        collapseSpeed: 1000,
        folderEvent: 'click',
        multiFolder: false
    }, function(file) {
        alert(file);
    });





});

$(document).ajaxComplete(function() {

    $('.edit').each(function() {
        $(this).editable('resources/script/jqueryFileTree/renameFile.jsp', {
            indicator : 'Saving...',
            event     : "rename",
            name : 'newValue',
            submitdata : {
                path: $(this).attr('rel'),
                oldValue: $(this).text()
            }
        });
    });





    $(".edit").contextMenu({
            menu: 'myMenu'
        },
        function(action, el, pos) {
            /*alert(
             'Action: ' + action + '\n\n' +
             'Element path: ' + $(el).attr('rel') + '\n\n'

             );*/
            if (action == "rename"){
                el.trigger("rename");
            }
        });
});