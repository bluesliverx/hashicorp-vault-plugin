package com.datapipe.jenkins.vault.credentials;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import io.github.jopenlibs.vault.Vault;
import io.github.jopenlibs.vault.VaultConfig;
import java.util.List;

public abstract class AbstractVaultTokenCredential
    extends BaseStandardCredentials implements VaultCredential {

    protected AbstractVaultTokenCredential(CredentialsScope scope, String id, String description) {
        super(scope, id, description);
    }

    protected abstract String getToken(Vault vault);

    @Override
    public VaultAuthorizationResult authorizeWithVault(VaultConfig config, List<String> policies) {
        Vault vault = Vault.create(config);
        String token = getToken(vault);
        return new VaultAuthorizationResult(Vault.create(config.token(token)), token);
    }
}
