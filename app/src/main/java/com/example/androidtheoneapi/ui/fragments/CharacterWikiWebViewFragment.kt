package com.example.androidtheoneapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.androidtheoneapi.databinding.FragmentCharacterWikiWebViewBinding
import com.example.androidtheoneapi.viewmodel.TheOneApiViewModel

class CharacterWikiWebViewFragment : Fragment() {
    private val viewModel: TheOneApiViewModel by viewModels()
    private lateinit var binding: FragmentCharacterWikiWebViewBinding
    val args: CharacterWikiWebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterWikiWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.characterWikiWebView.apply {
            webViewClient = WebViewClient()
            character.wikiUrl?.let {
                loadUrl(it)
            }
        }
    }
}