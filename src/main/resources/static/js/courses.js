$(document).ready(function () {
    var coursesTable = $("#courses_table");
    var teachers = [];

    var addCourseToTable = function (course) {
        coursesTable.append(
            '<tr data-courseUrl="' + course._links.self.href + '">' +
            '<td>' +
            '<span class="innoedit name">' + course.name + '</span>' +
            '<input class="inedit name"/>' +
            '</td>' +
            '<td>' +
            '<span class="innoedit description">' + course.description + '</span>' +
            '<input class="inedit description"/>' +
            '</td>' +
            '<td>' +
            '<span class="innoedit teacher">' + (course._embedded !== undefined ? course._embedded.teacher.name : 'Teacher not selected') + '</span>' +
            '<select class="inedit teacher">' +
            '<option selected disabled>Select teacher...</option>'
            + teacherSelect()
            + '</select> '
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


    function teacherSelect() {
        var options = "";
        for (var i = 0; i < teachers.length; i++) {
            options += '<option value="' + teachers[i].id + '">' + teachers[i].name + '</option>'
        }
        return options;
    };

    $.ajax({
        type: 'GET',
        url: '/courses',
        success: function (data) {
            $.each(data._embedded.courses, function (i, course) {
                addCourseToTable(course);
            });
        },
        error: function () {
            alert("Error loading data!");
        }
    });

    coursesTable.delegate("button.delete", "click", function () {
        row = $(this).closest("tr");
        $.ajax({
            type: 'DELETE',
            url: $(row).attr('data-courseurl'),
            success: function () {
                row.remove();
            },
            error: function () {
                alert("Can not delete row!");
            }
        });
    });

    coursesTable.delegate("button.edit", "click", function () {
        row = $(this).closest("tr");

        // copy from span to input
        row.find('input.name').val(row.find('span.name').html());
        row.find('input.description').val(row.find('span.description').html());

        row.addClass("inedit");
    });

    coursesTable.delegate("button.cancel", "click", function () {
        $(this).closest("tr").removeClass('inedit');
    });

    coursesTable.delegate("button.update", "click", function () {
        row = $(this).closest("tr");
        var name = row.find('input.name').val();
        var description = row.find('input.description').val();
        var teacherId = row.find('select.teacher').val();
        var teacher = teachers.find(function (data) {
            return data.id == teacherId;
        });
        var courseData = {
            name: name,
            description: description
        };

        $.ajax({
            type: 'PUT',
            url: $(row).attr('data-courseurl'),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(courseData),
            success: function () {
                if (teacher !== undefined) {
                    $.ajax({
                        type: 'PUT',
                        url: $(row).attr('data-courseurl') + '/teacher',
                        contentType: 'text/uri-list',
                        data: teacher._links.self.href,
                        success: function () {
                            row.find('span.name').html(name);
                            row.find('span.description').html(description);
                            row.find('span.teacher').html(teacher.name);
                        },
                        error: function () {
                            alert("Can not link teacher to course")
                        }
                    });
                }
            },
            error: function () {
                alert("Can not update!");
            }
        });
        row.removeClass("inedit");
    });

    $("#saveNewCourseBtn").on("click", function () {
        var name = $("#formCourseName").val();
        var description = $("#formCourseDescription").val();
        var courseData = {
            name: name,
            description: description,
        };

        $.ajax({
            type: 'POST',
            url: '/courses',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(courseData),
            success: function (course) {
                addCourseToTable(course);
            },
            error: function () {
                alert("Can not create new course!");
            }
        });
        $("#newCourseModal").modal('hide')
            .on('hidden.bs.modal', function (e) {
                $(this).find("input,textarea,select").val('').end();
            })
    });

    $.ajax({
        type: 'GET',
        url: '/persons',
        success: function (data) {
            $.each(data._embedded.persons, function (i, person) {
                teachers.push(person);
            });
        },
        error: function () {
            alert("Error loading data!");
        }
    });

});