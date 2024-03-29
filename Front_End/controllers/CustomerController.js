let customerId;
let customerName;
let customerMobile;
let customerCity;
let customerStreet;
let customerNIC;
let selectedId;

S(window).on('load', function () {
    S("#btn-update").prop("disabled", true);
    S("#btn-delete").prop("disabled", true);
    loadDataTable();
    setDataTableToTextFeild();
});

function getAllCustomerForTextFeild() {
    customerId = S('#customerId').val();
    customerName = S('#customerName').val();
    customerMobile = S('#customerMobile').val();
    customerCity = S('#customerCity').val();
    customerStreet = S('#customerStreet').val();
    customerNIC = S('#customerNIC').val();
}

S('#btn-save').click(function () {
    // if (checkAll()) {
    //     saveCustomer();
    // } else {
    //     alert("Error");
    // }
let data = S('#formData').serialize();
let id = S('#customerId').val();
let mobile = S('#customerMobile').val();
let nic = S('#customerNIC').val();
console.log(id)
console.log("Method Run")
S.ajax({
    url:"http://localhost:8080/app/customer",
    method:"POST",
    data:data,
    success: function (resp) {
        if (resp.status === 200) {
            loadDataTable();
            clearCustomerInputFields();
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Customer has been saved',
                showConfirmButton: false,
                timer: 2500
            })
        }else if (resp.status === 500 && resp.data.startsWith("Duplicate entry "+"'"+id+"'")){
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'Customer has been Already Exist',
                showConfirmButton: false,
                timer: 2500
            })
        }else if (resp.status === 500 && resp.data.startsWith("Duplicate entry "+"'"+mobile+"'")){
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'Mobile Number has been Already Exist',
                showConfirmButton: false,
                timer: 2500
            })
        }else if (resp.status === 500 && resp.data.startsWith("Duplicate entry "+"'"+nic+"'")){
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'NIC has been Already Exist',
                showConfirmButton: false,
                timer: 2500
            })
        }else {
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'Customer Not saved. Please Try Again',
                showConfirmButton: false,
                timer: 2500
            })
        }
    },
    error:function (resp) {
        Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'Customer Not saved. Please Try Again',
            showConfirmButton: false,
            timer: 2500
        })
    }

})
});

// function saveCustomer() {
//     getAllCustomerForTextFeild();
//     let customerIds = S('#customerId').val();
//     if (searchExistCustomer(customerIds.trim())) {
//         Swal.fire({
//             icon: 'error',
//             title: 'Oops...',
//             text: 'This Customer Already Exist.'
//         });
//     } else {
//         let mobileNum = S('#customerMobile').val();
//         if (searchExistCustomerMobile(mobileNum.trim())){
//             Swal.fire({
//                 icon: 'error',
//                 title: 'Oops...',
//                 text: 'This Customer Mobile Number Already Exist.'
//             });
//         }else {
//             let nic = S('#customerNIC').val();
//
//             if (searchExistCustomerNIC(nic.trim())){
//                 Swal.fire({
//                     icon: 'error',
//                     title: 'Oops...',
//                     text: 'This Customer NIC Already Exist.'
//                 });
//             }else {
//                 let newCustomer = Object.assign({}, customer);
//
//                 newCustomer.id = customerId;
//                 newCustomer.name = customerName;
//
//                 newCustomer.mobile = customerMobile;
//                 newCustomer.nic = customerNIC;
//                 newCustomer.address = customerStreet + ", " + customerCity;
//
//                 customerDB.push(newCustomer);
//
//                 Swal.fire({
//                     position: 'top-end',
//                     icon: 'success',
//                     title: 'Customer has been saved',
//                     showConfirmButton: false,
//                     timer: 1500
//                 })
//
//                 loadDataTable();
//                 clearCustomerInputFields();
//                 setDataTableToTextFeild();
//                 doubleClick();
//                 setCustomerId();
//                 S('#search').val("");
//             }
//         }
//
//     }
// }

// function searchExistCustomer(id) {
//     return customerDB.find(function (customer) {
//         return customer.id == id;
//     });
// }

// function searchExistCustomerMobile(mobile) {
//     return customerDB.find(function (customer) {
//         return customer.mobile == mobile;
//     });
// }
//
// function searchExistCustomerNIC(nic) {
//     return customerDB.find(function (customer) {
//         return customer.nic == nic;
//     });
// }

function loadDataTable() {
    S('#tBody').empty();
    S.ajax({
        url: "http://localhost:8080/app/customer",
        method: "GET",
        dataType:"json",
        success: function (resp) {
            console.log(resp)
            for (var customer of resp) {
                console.log(customer.street)
                var row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.street+", "+customer.city}</td><td>${customer.mobile}</td><td>${customer.nic}</td></tr>`;
                S('#tBody').append(row)
                setDataTableToTextFeild();
                doubleClick();
            }
        }
    })
}

function setDataTextFeild(id, name, mobile, nic, city, street) {
    S('#customerId').val(id);
    S('#customerName').val(name);
    S('#customerMobile').val(mobile);
    S('#customerCity').val(city);
    S('#customerStreet').val(street);
    S('#customerNIC').val(nic);
}

function setDataTableToTextFeild() {
    S('#tBody > tr').click(function () {
        let id = S(this).children(':eq(0)').text();
        let name = S(this).children(':eq(1)').text();
        let address = S(this).children(':eq(2)').text();
        let mobile = S(this).children(':eq(3)').text();
        let nic = S(this).children(':eq(4)').text();

        let splitAddress = address.split(',')

        let street = splitAddress[0];
        let city = splitAddress[1];

        setDataTextFeild(id, name, mobile, nic, city, street);
        S('#customerId').prop('disabled', true);
        selectedId = S('#customerId').val();
        // setId(id);
        setBtn();
        S('#search').val("");
    });
}

S('#btn-update').click(function () {

    // if (checkAll()) {
    //     updateCustomer();
    // } else {
    //     alert('error');
    // }
    var cusOB = {
        id:S('#customerId').val(),
        name:S('#customerName').val(),
        mobile:S('#customerMobile').val(),
        nic:S('#customerNIC').val(),
        city:S('#customerCity').val(),
        street:S('#customerStreet').val(),
    }
    let data = S('#formData').serialize();
    let id = S('#customerId').val();
    let mobile = S('#customerMobile').val();
    let nic = S('#customerNIC').val();
    // console.log(data)
    S.ajax({
        url: "http://localhost:8080/app/customer",
        method: "PUT",
        contentType: "application/json",
        data:JSON.stringify(cusOB),
        success: function (resp) {
            if (resp.status === 200) {
                loadDataTable();
                clearCustomerInputFields();
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Customer has been Updated!',
                    showConfirmButton: false,
                    timer: 2500
                })
                // }else if (resp.status === 500 && resp.data.startsWith("Duplicate entry "+"'"+id+"'")){
                //     Swal.fire({
                //         position: 'top-end',
                //         icon: 'warning',
                //         title: 'Customer has been Already Exist',
                //         showConfirmButton: false,
                //         timer: 2500
                //     })
            } else if (resp.status === 500 && resp.data.startsWith("Duplicate entry " + "'" + mobile + "'")) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'warning',
                    title: 'Mobile Number has been Already Exist',
                    showConfirmButton: false,
                    timer: 2500
                })
            } else if (resp.status === 500 && resp.data.startsWith("Duplicate entry " + "'" + nic + "'")) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'warning',
                    title: 'NIC has been Already Exist',
                    showConfirmButton: false,
                    timer: 2500
                })
            } else {
                Swal.fire({
                    position: 'top-end',
                    icon: 'warning',
                    title: 'Customer Not saved. Please Try Again',
                    showConfirmButton: false,
                    timer: 2500
                })
            }
        },
        error: function (resp) {
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'Customer Not saved. Please Try Again',
                showConfirmButton: false,
                timer: 2500
            })
        }
    });
});

// function updateCustomer() {
//     getAllCustomerForTextFeild();
//     Swal.fire({
//         title: 'Do you want to save the changes?',
//         showDenyButton: true,
//         showCancelButton: true,
//         confirmButtonText: 'Save',
//         denyButtonText: `Don't save`,
//     }).then((result) => {
//         /* Read more about isConfirmed, isDenied below */
//         if (result.isConfirmed) {
//             Swal.fire('Saved!', '', 'success');
//
//             let index = -1;
//
//             for (let customerObj of customerDB) {
//                 if (customerObj.id == selectedId) {
//                     index = customerDB.indexOf(customerObj);
//                 }
//             }
//
//             customerDB[index].id = customerId;
//             customerDB[index].name = customerName;
//             customerDB[index].address = customerStreet + ", " + customerCity;
//             customerDB[index].mobile = customerMobile;
//             customerDB[index].nic = customerNIC;
//             loadDataTable();
//             clearCustomerInputFields();
//             S('#customerId').prop('disabled', false);
//             setDataTableToTextFeild();
//             doubleClick();
//             S('#search').val("");
//
//         } else if (result.isDenied) {
//             Swal.fire('Changes are not saved', '', 'info')
//         }
//     });
// }

function disableTextFeild(condition) {
    S('#customerId').prop('disabled', condition);
    S('#customerName').prop('disabled', condition);
    S('#customerMobile').prop('disabled', condition);
    S('#customerCity').prop('disabled', condition);
    S('#customerStreet').prop('disabled', condition);
    S('#customerNIC').prop('disabled', condition);
}

function doubleClick() {
    S('#tBody > tr').on('dblclick', function () {
        disableTextFeild(true);
        S("#btn-delete").prop("disabled", false);
        S("#btn-save").prop("disabled", true);
        S("#btn-update").prop("disabled", true);
    });
}

S('#btn-delete').click(function () {
    // deleteCustomer();
    let id = S('#customerId').val();
    S.ajax({
        url: "http://localhost:8080/app/customer?id="+id,
        method:"DELETE",
        success: function (resp) {
            if (resp.status === 200) {
                clearCustomerInputFields();
                loadDataTable();
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Customer has been Deleted!',
                    showConfirmButton: false,
                    timer: 2500
                })
            }else {
                Swal.fire({
                    position: 'top-end',
                    icon: 'warning',
                    title: 'Customer Delete Unsuccessful. Please Try Again',
                    showConfirmButton: false,
                    timer: 2500
                })
            }
        },
        error: function (resp) {
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'Customer Delete Unsuccessful. Please Try Again',
                showConfirmButton: false,
                timer: 2500
            })
        }
    })
});

function deleteCustomer() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                'Deleted!',
                'Your file has been deleted.',
                'success'
            )
            customerDB.splice(selectedId, 1);
            loadDataTable();
            clearCustomerInputFields();
            setDataTableToTextFeild();
            doubleClick();
            disableTextFeild(false);
            S('#search').val("");
        }
    })
}

S('#btn-cleartable').click(function () {
    S('#tBody').empty();
    clearCustomerInputFields();
    disableTextFeild(false);
    S('#search').val("");
});

S('#btn-getAll').click(function () {
    loadDataTable();
    setDataTableToTextFeild();
    doubleClick();
    S('#search').val("");
});
function searchCustomer() {
    S('#search').on('keyup', function () {
        S('#tBody').empty();
        let index = -1;

        for (let customerObj of customerDB) {
            if (customerObj.id == S('#search').val()) {
                index = customerDB.indexOf(customerObj);
            }
        }
        var row = `<tr><td>${customerDB[index].id}</td><td>${customerDB[index].name}</td><td>${customerDB[index].address}</td><td>${customerDB[index].mobile}</td><td>${customerDB[index].nic}</td></tr>`;
        S('#tBody').append(row)
        setDataTableToTextFeild();
        doubleClick();
    });

}


