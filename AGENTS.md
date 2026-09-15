# QC bootstrap

Áp dụng cho mọi QC/QA task trong thư mục này và các thư mục con: phân tích requirement, test case, review, thực thi test, automation, bug và báo cáo.

1. Trước khi phân tích hoặc thực hiện QC task, đọc đầy đủ [QC master skill](.agents/skills/qc-master/SKILL.md). Đây là nguồn instruction QC chính thức của repo; không dựa vào bản nhớ hoặc bản tóm tắt từ phiên trước.
2. Resolve đường dẫn master theo thư mục chứa AGENTS.md này, không theo working directory hiện tại. Kiểm tra thêm instruction trong thư mục con liên quan trước khi thao tác.
3. Nếu master không tồn tại hoặc không đọc được, báo rõ đường dẫn và lỗi; dừng phần QC phụ thuộc vào master, không tự tạo quy tắc thay thế.
4. Đầu task xác nhận ngắn: “Đã đọc QC master: <đường dẫn>; phạm vi: <task>”. Sau đó làm đúng phạm vi người dùng giao và workflow tương ứng trong master.
5. Instruction cấp hệ thống/developer và yêu cầu rõ ràng của người dùng được ưu tiên. Nếu instruction repo mâu thuẫn nhau làm thay đổi expected result hoặc phạm vi, nêu mâu thuẫn và chỉ hỏi phần cần quyết định; tiếp tục phần độc lập.

## Agent không tự đọc AGENTS.md

Trong cấu hình khởi động/system prompt của agent đó, thêm: “Trước mọi QC/QA task tại repo này, hãy đọc AGENTS.md ở root và .agents/skills/qc-master/SKILL.md, rồi tuân thủ instruction áp dụng trước khi thực hiện task.”

Đây là cơ chế hướng dẫn dành cho agent hỗ trợ repo instructions, không phải runtime hook bảo đảm mọi bot đều đọc file. Chỉ cấu hình adapter riêng khi xác định được công cụ đang sử dụng; không nhân bản nội dung master sang nhiều file.
