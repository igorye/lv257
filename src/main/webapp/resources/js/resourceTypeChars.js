const propertiesForm = $('#form_for_properties');
const resourcePropsLabel = $('#resource_prop_label');
const registerRecourceBtn = $('#register_resource_btn');
const $alert = $('.cuslom_alert');

const resourceUniquenessFieldsErrors = $('.resource_uniqueness_exception');
const someException = $('.some_exception');
let resourceTypeId;
$(document).ready(function () {

    propertiesForm.hide();
    resourcePropsLabel.hide();

    if ($alert.length) {
        setTimeout(function () {
            $alert.fadeOut('slow');
        }, 5000)
    }

    $('#types').on('change', function (e) {
        resourceTypeId = $(e.target).data('selectedID');

        console.log(resourceTypeId);

        ajaxCallToShowProperties(resourceTypeId);

    });

    registerRecourceBtn.on('click', function (e) {
        e.preventDefault();

        if (emptyOwners()) {
            alert('Please specify at least one owner');
        }
        if (emptyAddress()) {
            alert('Please specify resource address');
        }
        if (emptyResourceType()) {
            alert('Please specify resource type');
        }

        console.log("properties form is: " + propertiesForm.valid());
        if (propertiesForm.valid() &&
            !emptyOwners() &&
            !emptyResourceType() &&
            !emptyAddress()) {
            saveResourceAjaxCall();
        }
    })


});


function emptyOwners() {
    let find = $ownersTbody.find('tr');

    if (find.length === 0) {
        return true;
    }
}


function emptyAddress() {
    let addressId = $idAddressHiddenInput.attr('value');

    if (addressId == 0 || addressId === undefined) {
        return true;
    }
}

function emptyResourceType() {
    if (resourceTypeId === undefined || resourceTypeId === 0) {
        return true;
    }
}

function saveResourceAjaxCall() {
    let resourceJson = prepareResourceJson();

    $.ajax({
        type: 'POST',
        url: projectPathPrefix + '/resources/registration',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(resourceJson),
        success: function (result) {
            console.log(result);

            if (result.field){
                window.location.href = result.message;
            }

        },
        error: function (result) {
            console.log(result);
            if (result.status == 400) {
                let parse = JSON.parse(result.responseText);
                console.log('errors in fields: ' + parse);
                $('.my_error_class').empty();

                appendHibernateErrors(parse);
            }
            if (result.status == 403) {
                resourceUniquenessFieldsErrors.show(500);
                resourceUniquenessFieldsErrors.delay(10000).hide(500);
            }
            if (result.status == 500) {
                someException.show(500);
                someException.delay(10000).hide(500);
            }
        }

    });
}

function prepareResourceJson(){
    let resourceInfoJson = {};
    resourceInfoJson['resourceTypeId'] = resourceTypeId;
    resourceInfoJson['addressId'] = $idAddressHiddenInput.attr('value');

    let find = $ownersTbody.find('tr');
    let ownersIds = [];
    for (let i = 0; i < find.length; i++) {
        ownersIds[i] = $(find[i]).attr('id');
    }
    resourceInfoJson['ownerIds'] = ownersIds;

    let propertiesAndValues = toJSON(propertiesForm.attr('id'));
    resourceInfoJson['propertiesAndValues'] = propertiesAndValues;

    console.log(resourceInfoJson);

    return resourceInfoJson;
}

function ajaxCallToShowProperties(resourceTypeId) {
    $.ajax({
        type: 'GET',
        url: projectPathPrefix + '/resources/api/' + resourceTypeId,
        success: function (constrainedProperties) {
            propertiesForm.empty();
            console.log(constrainedProperties);
            buildInputsAndValidate(constrainedProperties);
        },
        error: function (result) {
            // todo
        }

    });

}

function buildInputsAndValidate(constrainedProperties) {
    for (let i = 0; i < constrainedProperties.length; i++) {
        let property = constrainedProperties[i].property;
        console.log(property);

        let $col = $('<div/>', {
            class: 'col-sm-12',
        });
        propertiesForm.append($col);

        let $formGroup = $('<div/>', {
            class: 'form-group'
        });
        $col.append($formGroup);

        let $label = $('<label/>', {
            for: property.columnName,
            text: property.title
        });
        $formGroup.append($label);

        let $input = $('<input/>', {
            type: 'text',
            class: 'form-control',
            pattern: property.pattern,
            name: property.columnName,
            id: property.columnName,
        });
        $formGroup.append($input);

        let $error = $('<div/>', {
            class: 'my_error_class'
        });

        $formGroup.append($error);
        let $small = $('<small/>', {
            class: 'form-text text-muted',
            text: property.hint
        });
        $formGroup.append($small);
    }

    propertiesForm.show(500);
    resourcePropsLabel.show(500);

    populateUniqueConstrainsErrorMessage(constrainedProperties);

    validateProperties(constrainedProperties);
}


function populateUniqueConstrainsErrorMessage(constrainedProperties){
    let text = "";
    for (let i = 0; i < constrainedProperties.length; i++) {
        if (constrainedProperties[i].unique){
            text += constrainedProperties[i].property.title + " .. ";
        }
    }

    resourceUniquenessFieldsErrors.find('.alert').text(
        'Some of the values in this fields already exists: ' + text + ' ' +
        'Please, try to specify different one\'s'
    );
}

function validateProperties(constrainedProperties) {
    let rules = {};
    for (let i = 0; i < constrainedProperties.length; i++) {
        let constrainedProperty = constrainedProperties[i];
        rules[constrainedProperty.property.columnName] = {'required': constrainedProperty.required};
    }
    console.log(rules);

    propertiesForm.validate({
        errorClass: "my_error_class",
        rules: rules
    });
}