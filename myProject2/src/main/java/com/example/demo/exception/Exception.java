package com.example.demo.exception;

public enum Exception {
    // Auth
    MEMBER_NOT_FOUND_EXCEPTION("사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH_EXCEPTION("비밀번호가 일치하지 않습니다."),
    SIGNUP_USERNAME_DUPLICATE_EXCEPTION("이미 존재하는 아이디입니다."),

    // Recipe
    RECIPE_NOT_EXIST_EXCEPTION("레시피가 존재하지 않습니다."),

    // column
    ONLY_WRITE_COLUMN_BY_PHARMACIST("약사만 컬럼을 작성할 수 있습니다"),
    NOT_EXIST_COLUMN("존재하지 않는 칼럼입니다."),
    NOT_EXIST_PHARMACIST("해당 약사는 존재하지 않습니다"),
    ONLY_EDIT_COLUMN_BY_WRITER("칼럼 작성자만 수정 및 삭제 할 수 있습니다."),

    //post
    NOT_EXIST_POST("해당 게시글이 존재하지 않습니다."),
    ONLY_EDIT_POST_BY_WRITER("작성자만 수정 및 삭제할 수 있습니다."),

    // Calendar
    NOT_EXIST_INTAKE_RECORD("해당 날짜의 복용 기록이 없습니다."),

    //Comment
    NOT_EXIST_COMMENT("존재하지 않는 댓글입니다."),
    ONLY_EDIT_COMMENT_BY_WRITER("댓글 작성자만 수정 및 삭제할 수 있습니다."),

    //Follow
    NOT_EXIST_FOLLOW("해당 팔로우는 존재하지 않습니다."),

    //Notification
    NOT_EXIST_NOTIFICATION("해당 알림이 존재하지 않습니다.")


    ;
    private static final String PREFIX = "[ERROR] ";
    private final String message;

    Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
