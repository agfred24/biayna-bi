package com.biayna.bi.domain.user.accounts;

public interface PermissionChecker {
	
	User checkCredentials(final LoginVO user);
}
