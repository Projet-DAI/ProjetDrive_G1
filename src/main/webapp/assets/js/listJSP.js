	// 打开模态窗
    function openModal() {
        var modal = document.getElementById("myModal");
        modal.style.display = "block";
    }

    // 关闭模态窗
    function closeModal() {
        var modal = document.getElementById("myModal");
        modal.style.display = "none";
    }

    // 点击模态窗外部区域时关闭模态窗
    window.onclick = function(event) {
        var modal = document.getElementById("myModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    
