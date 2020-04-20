$(document).ready(function () {
    var personsTable = $("#teacher_table");

    var addTeacherToTable = function (person) {
        personsTable.append(
            '<tr data-personUrl="' + person._links.self.href + '">' +
            '<td>' +
            '<span class="innoedit firstName">' + person.firstName + '</span>' +
            '<input class="inedit firstName"/>'
            + '</td>' +
            '<td>' +
            '<span class="innoedit lastName">' + person.lastName + '</span>' +
            '<input class="inedit lastName"/>'
            + '</td>' +
            '<td>' +
            '<span class="innoedit phone">' + person.phone + '</span>' +
            '<input class="inedit phone"/>'
            + '</td>' +
            '<td>' +
            '<span class="innoedit room">' + person.room + '</span>' +
            '<input class="inedit room"/>'
            + '</td>' +
            '<td>' +
            '  <button class="btn btn-sm btn-warning edit innoedit">Edit</button>' +
            '  <button class="btn btn-sm btn-danger delete innoedit">Delete</button>' +
            '  <button class="btn btn-sm btn-danger update inedit">Update</button>' +
            '  <button class="btn btn-sm btn-success cancel inedit">Cancel</button>' +
            '</td>' +
            '</tr>'
        );
    }

    $.ajax({
        type: 'GET',
        url: '/persons',
        success: function (data) {
            $.each(data._embedded.persons, function (i, person) {
                $.ajax({
                    type: 'GET',
                    url: person._links.self.href,
                    success: function (p) {
                        addTeacherToTable(p);
                    },
                    error: function () {
                        alert("Error loading data!");
                    }
                })
            });
        },
        error: function () {
            alert("Error loading data!");
        }
    });

    personsTable.delegate("button.delete", "click", function () {
        row = $(this).closest("tr");
        $.ajax({
            type: 'DELETE',
            url: $(row).attr('data-personurl'),
            success: function (data) {
                row.remove();
            },
            error: function () {
                alert("Can not delete row! Person is teaching on a course!");
            }
        });
    });

    personsTable.delegate("button.edit", "click", function () {
        row = $(this).closest("tr");
        row.find('input.firstName').val(row.find('span.firstName').html());
        row.find('input.lastName').val(row.find('span.lastName').html());
        row.find('input.phone').val(row.find('span.phone').html());
        row.find('input.room').val(row.find('span.room').html());
        row.addClass("inedit");
    });

    personsTable.delegate("button.cancel", "click", function () {
        $(this).closest("tr").removeClass("inedit");
    });

    personsTable.delegate("button.update", "click", function () {
        row = $(this).closest("tr");

        var firstName = row.find('input.firstName').val();
        var lastName = row.find('input.lastName').val();
        var phone = row.find('input.phone').val();
        var room = row.find('input.room').val();
        var personData = {
            firstName: firstName,
            lastName: lastName,
            phone: phone,
            room: room
        };

        $.ajax({
            type: 'PUT',
            url: $(row).attr('data-personurl'),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(personData),
            success: function () {
                row.find('span.firstName').html(firstName);
                row.find('span.lastName').html(lastName);
                row.find('span.phone').html(phone);
                row.find('span.room').html(room);
            },
            error: function () {
                alert("Can not update!");
            }
        });
        row.removeClass("inedit");
    });

    $("#saveNewTeacherBtn").on("click", function () {
        var firstName = $("#formTeacherFirstName").val();
        var lastName = $("#formTeacherLastName").val();
        var phone = $("#formTeacherPhone").val();
        var room = $("#formTeacherRoom").val();
        var personData = {
            firstName: firstName,
            lastName: lastName,
            phone: phone,
            room: room
        };

        $.ajax({
            type: 'POST',
            url: '/persons',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(personData),
            success: function (person) {
                addTeacherToTable(person);
            },
            error: function () {
                alert("Can not create new teacher!");
            }
        });
        $("#newTeacherModal").modal('hide')
            .on('hidden.bs.modal', function (e) {
                $(this).find("input,textarea,select").val('').end();
            })
    });

});