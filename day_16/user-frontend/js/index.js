const API_URL = "http://localhost:8080/api/v1/users";

// Truy cập vào các thành phần
const tableContentEl = document.querySelector("tbody");

// Lưu lại ds user
let users = [];

// Lấy danh sách user
const getUsers = async () => {
    try {
        let res = await axios.get(API_URL);
        users = res.data;
        console.log(users);

        renderPaginationAndUser(users);
    } catch (error) {
        console.log(error);
    }
};

// Hiển thị ds user
const renderUser = (arr, pagination) => {
    tableContentEl.innerHTML = "";

    let html = "";  
    arr.forEach((u, i) => {
        html += `
            <tr>
                <td>${pagination.pageSize * (pagination.pageNumber - 1) + (i + 1)}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.address}</td>
                <td>
                    <a href="./detail.html?id=${u.id}" class="btn btn-success">Xem chi tiết</a>
                    <button class="btn btn-danger">Xóa</button>
                </td>
            </tr>
        `;
    });
    tableContentEl.innerHTML = html;
};

// Hiển thị phân trang
const renderPaginationAndUser = (arr) => {
    $(".pagination-container").pagination({
        dataSource: arr,
        pageSize: 5,
        // showPrevious : false,
        // showNext : false,
        callback: function (data, pagination) {
            console.log(data);
            console.log(pagination);
            renderUser(data, pagination);
        },
    });
};

getUsers();
